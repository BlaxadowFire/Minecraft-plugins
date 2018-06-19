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
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            String lowerCmd = cmd.getName().toLowerCase();
            switch (lowerCmd) {
                case "givesword":
                    Inventory inv = player.getInventory();
                    inv.addItem(new ItemStack(Material.DIAMOND, 1));
                    break;
                case "clearinv":
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Clear inventory??");
                    break;
                case "monster":
                    Location sploc = player.getLocation().add(2,0,0);
                    World world = player.getWorld();
                    Spider spider = (Spider) world.spawnEntity(sploc, EntityType.SPIDER);
                    Skeleton skeleton = (Skeleton) world.spawnEntity(sploc, EntityType.SKELETON);
                    spider.setPassenger(skeleton);
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
