package tk.blaxadowfire.minecraft.testserver.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModeCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }
        Player player = (Player) commandSender;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("survival"))
                player.setGameMode(GameMode.SURVIVAL);
            else if (args[0].equalsIgnoreCase("creative"))
                player.setGameMode(GameMode.CREATIVE);
            else
                return false;
            commandSender.sendMessage(ChatColor.GREEN + "Gamemode has been changed.");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (!(commandSender instanceof Player))
            return list;

        Player player = (Player) commandSender;

        if (args.length - 1 == 0) {
            String[] values = {"survival", "creative"};
            for (String value : values) {
                if (value.toLowerCase().startsWith(args[0].toLowerCase()))
                    list.add(value);
            }
        }
        return list;
    }
}
