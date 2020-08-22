package tk.blaxadowfire.minecraft.testserver.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import tk.blaxadowfire.minecraft.testserver.TestServerPlugin;

public class EventsClass implements Listener {
    private Plugin plugin = TestServerPlugin.getPlugin(TestServerPlugin.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        player.sendMessage(ChatColor.RED + "The regular server has been temporarily replaced by a test server. All progress and changes made here won't effect the regular server.");
        player.sendMessage(ChatColor.GOLD + "Contact server admin if you want to play on the normal server.");
        player.sendMessage(ChatColor.GREEN + "You can set your gamemode with /gm");


        plugin.getConfig().set("Users." + player.getUniqueId() + ".Username", player.getDisplayName());
        plugin.saveConfig();
    }
}
