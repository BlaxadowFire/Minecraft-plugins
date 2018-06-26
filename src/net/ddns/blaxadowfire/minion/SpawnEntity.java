package net.ddns.blaxadowfire.minion;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SpawnEntity implements Listener, CommandExecutor {
    private Main plugin;
    private Minion minion;
    public SpawnEntity(Main plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            String lowerCmd = cmd.getName().toLowerCase();
            Inventory inv = player.getInventory();
            switch (lowerCmd) {
                case "givesword":
                    inv.addItem(new ItemStack(Material.DIAMOND_SWORD,1));
                    break;
                case "givearmor":
                    inv.addItem(new ItemStack(Material.DIAMOND_HELMET, 1));
                    inv.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE,1));
                    inv.addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    inv.addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    break;
                case "monster":
                    Location sploc = player.getLocation().add(2,0,0);
                    World world = player.getWorld();
                    Spider spider = (Spider) world.spawnEntity(sploc, EntityType.SPIDER);
                    Skeleton skeleton = (Skeleton) world.spawnEntity(sploc, EntityType.SKELETON);
                    spider.addPassenger(skeleton);
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Monster Spawned");
                    break;
                case "minion":
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Minion Called");
                    minion = new Minion(player.getLocation(), player.getUniqueId());
                    break;
                case "mine":
                    minion = new Minion(player.getLocation(), player.getUniqueId());
                    minion.Mining();
                    break;

            }
        }
        else
        {
            player.sendMessage("command not issued by player!!");
        }
        return true;
    }
}
