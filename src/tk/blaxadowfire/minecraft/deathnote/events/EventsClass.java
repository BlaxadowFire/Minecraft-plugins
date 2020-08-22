package tk.blaxadowfire.minecraft.deathnote.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import tk.blaxadowfire.minecraft.deathnote.DeathNotePlugin;
import tk.blaxadowfire.minecraft.deathnote.items.DeathNote;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventsClass implements Listener {
    Plugin plugin = DeathNotePlugin.getPlugin(DeathNotePlugin.class);

    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent event){
        Player player = event.getPlayer();
        BookMeta bm = event.getNewBookMeta();
        ItemStack deathNote = new DeathNote().getDeathNote();
        BookMeta deathNoteMeta = (BookMeta) deathNote.getItemMeta();

        if (!bm.getTitle().equals(deathNoteMeta.getTitle()))
            return;
        if (!((bm.getPage(1)).equalsIgnoreCase(deathNoteMeta.getPage(1)) && (bm.getPage(2)).equalsIgnoreCase(deathNoteMeta.getPage(2))))
            return;
        List<String> pages = new LinkedList<>(bm.getPages());
        pages.remove(0);
        pages.remove(0);
        player.sendMessage("Written names:");
        for (String page : pages) {
            pageHandler(player, pages, page);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!plugin.getConfig().getConfigurationSection("DeathNote.Targets.UUIDs").getKeys(false).contains(player.getUniqueId().toString())) {
            return;
        }
        Bukkit.getServer().broadcastMessage("test");
        if(plugin.getConfig().getInt("DeathNote.Targets.UUIDs." + player.getUniqueId() + ".Day") != (Bukkit.getServer().getWorld("world").getFullTime()/24000)) {
            return;
        }
        event.setDeathMessage(player.getName() + " " + plugin.getConfig().getString("DeathNote.Targets.UUIDs." + player.getUniqueId() + ".Cause"));
        plugin.getConfig().getConfigurationSection("DeathNote.Targets.UUIDs").set(player.getUniqueId().toString(), null);
        plugin.saveConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "player: " + player.getName() + " joined");
        if (!plugin.getConfig().getConfigurationSection("DeathNote.Targets.Names").getKeys(false).contains(player.getName()))
            return;

        plugin.getConfig().getConfigurationSection("DeathNote.Targets.Names").set(player.getName(), null);

        int day = plugin.getConfig().getInt("DeathNote.Targets.Names." + player.getName() + ".Day");
        plugin.getConfig().set("DeathNote.Targets.UUIDs." + player.getUniqueId().toString() + ".Day", day);
        plugin.saveConfig();
    }

    private void pageHandler(Player player, List<String> pages, String page) {
        String playerPatternString = "\\[player:(.*)\\]";
        String dayPatternString = "\\[day:(.*)\\]";
        String causePatternString = "\\[cause:(.*)\\]";
        Pattern playerPattern = Pattern.compile(playerPatternString);
        Pattern dayPattern = Pattern.compile(dayPatternString);
        Pattern causePattern = Pattern.compile(causePatternString);
        Matcher playerMatcher = playerPattern.matcher(page);
        Matcher dayMatcher = dayPattern.matcher(page);
        Matcher causeMatcher = causePattern.matcher(page);

        player.sendMessage("#"+pages.indexOf(page));

        String targetPlayer = "";
        String targetDay = Bukkit.getServer().getWorld("world").getFullTime()/24000 + "";
        String targetCause = "died of a heart attack";

        if (playerMatcher.find()) {
            targetPlayer = playerMatcher.group(1);
            player.sendMessage(ChatColor.GOLD + "You targeted: " + ChatColor.GREEN + targetPlayer);
        }
        if (dayMatcher.find()){
            targetDay = dayMatcher.group(1);
            player.sendMessage(ChatColor.GOLD + "On day: " + ChatColor.BLUE + targetDay);
        }
        if (causeMatcher.find()) {
            targetCause = causeMatcher.group(1);
            player.sendMessage(ChatColor.GOLD + "With cause: " + ChatColor.DARK_PURPLE + targetCause);
        }

        if (targetPlayer.equals(""))
            return;

        boolean playerFound = false;

        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
            if (!onlinePlayer.getName().equalsIgnoreCase(targetPlayer))
                continue;
            plugin.getConfig().set("DeathNote.Targets.UUIDs." + onlinePlayer.getUniqueId() + ".Day", Integer.parseInt(targetDay));
            plugin.getConfig().set("DeathNote.Targets.UUIDs." + onlinePlayer.getUniqueId() + ".Cause", targetCause);
            playerFound = true;
            break;
        }
        if (!playerFound){
            plugin.getConfig().set("DeathNote.Targets.Names." + targetPlayer + ".Day", Integer.parseInt(targetDay));
            plugin.getConfig().set("DeathNote.Targets.Names." + targetPlayer + ".Cause", targetCause);
        }
        plugin.saveConfig();
    }
}
