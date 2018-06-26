package net.ddns.blaxadowfire.minion.commands;

import net.ddns.blaxadowfire.minion.Minion;
import net.ddns.blaxadowfire.minion.MinionTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinionCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            if(args.length != 1)
                sender.sendMessage(ChatColor.RED + "Please give minion task");
            Player player = (Player) sender;

            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Minion Called");
            Minion minion = new Minion(player.getLocation(), player.getUniqueId(), MinionTask.valueOf(args[0].toUpperCase()));
        }
        else
            sender.sendMessage(ChatColor.RED + "Players only!");
        return true;
    }
}
