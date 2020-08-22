package tk.blaxadowfire.minecraft.customcompass.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tk.blaxadowfire.minecraft.customcompass.CustomCompassPlugin;

import java.util.ArrayList;
import java.util.List;

public class CustomCompassPointCommand implements TabExecutor {
    private Plugin plugin = CustomCompassPlugin.getPlugin(CustomCompassPlugin.class);

    private enum FirstArg {
        ADD("add"),
        EDIT("edit"),
        REMOVE("remove"),
        USE("use"),
        RENAME("rename"),
        LIST("list");
        private String value;
        FirstArg(String value){
            this.value = value;
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) commandSender;
        List<String> customCompassPoints = getAllKeysFromConfigPath("Users." + player.getUniqueId() + ".CustomCompassPoints");

        if (args.length < 1)
            return false;

        if (args.length == 1) {
            if (!args[0].equalsIgnoreCase(FirstArg.LIST.value))
                return false;
            StringBuilder message = new StringBuilder(ChatColor.LIGHT_PURPLE + "Custom Compass Points: \n" + ChatColor.BLUE);
            for (String customCompassPoint : customCompassPoints)
                message.append(customCompassPoint).append("\n");
            player.sendMessage(message.toString());
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "add":
                for (String customCompassPoint : customCompassPoints) {
                    if (customCompassPoint.equalsIgnoreCase(args[1])) {
                        commandSender.sendMessage(
                                ChatColor.RED + "There is already a point set with this name.");
                        return true;
                    }
                }
                if (setCustomCompassPoint(player, args[1], null))
                    commandSender.sendMessage(ChatColor.GOLD + "Custom Compass Point has been added.");
                return true;

            case "remove":
                for (String customCompassPoint : customCompassPoints) {
                    if (customCompassPoint.equalsIgnoreCase(args[1])) {
                        plugin.getConfig().set("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPoint, null);
                        plugin.saveConfig();
                        commandSender.sendMessage(ChatColor.RED + "Point has been removed.");
                        return true;
                    }
                }
                commandSender.sendMessage(ChatColor.RED + "Point doesn't exist.");
                return true;

            case "use":
                for (String customCompassPoint : customCompassPoints) {
                    if (customCompassPoint.equalsIgnoreCase(args[1])) {
                        Location location = plugin.getConfig().getLocation("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPoint);
                        if (location == null) {
                            commandSender.sendMessage(ChatColor.RED + "Point is empty, try setting it again.");
                            return true;
                        }
                        player.setCompassTarget(location);
                        commandSender.sendMessage(ChatColor.GOLD + "Point has been set.");
                        return true;
                    }
                }
                commandSender.sendMessage(ChatColor.RED + "Point doesn't exist.");
                return true;

            case "edit":
                for (String customCompassPoint : customCompassPoints){
                    if (customCompassPoint.equalsIgnoreCase(args[1])){
                        if (setCustomCompassPoint(player, customCompassPoint, null))
                            commandSender.sendMessage(ChatColor.GOLD + "Point has been edited.");
                        return true;
                    }
                }
                commandSender.sendMessage(ChatColor.RED + "Point doesn't exist.");
                return true;

            case "rename":
                for (String customCompassPoint : customCompassPoints){
                    if (customCompassPoint.equalsIgnoreCase(args[1])){
                        if (setCustomCompassPoint(player, customCompassPoint, args[2]))
                            commandSender.sendMessage(ChatColor.GOLD + "Point has been renamed.");
                        return true;
                    }
                }
                commandSender.sendMessage(ChatColor.RED + "Point doesn't exist.");
                return true;

            default:
                return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (!(commandSender instanceof Player))
            return list;

        Player player = (Player) commandSender;

        switch (args.length - 1) {
            case 0:
                String[] values = {"add", "remove", "use", "edit", "rename", "list"};
                for (String value : values) {
                    if (value.toLowerCase().startsWith(args[0].toLowerCase()))
                        list.add(value);
                }
                break;
            case 1:
                if (!args[0].equalsIgnoreCase("remove") && !args[0].equalsIgnoreCase("use") &&
                        !args[0].equalsIgnoreCase("edit") && !args[0].equalsIgnoreCase("rename"))
                    break;
                List<String> customCompassPointNames = new ArrayList<>();
                customCompassPointNames.addAll(plugin.getConfig().getConfigurationSection("Users." + player.getUniqueId() + ".CustomCompassPoints").getKeys(false));

                for (String customCompassPointName : customCompassPointNames) {
                    if (customCompassPointName.toLowerCase().startsWith(args[1].toLowerCase()))
                        list.add(customCompassPointName);
                }
                break;
        }
        return list;
    }

    public List<String> getAllKeysFromConfigPath(String path) {
        List<String> customCompassPoints = new ArrayList<>();
        customCompassPoints.addAll(plugin.getConfig().getConfigurationSection(path).getKeys(false));
        return customCompassPoints;
    }
    public Boolean setCustomCompassPoint(Player player, String customCompassPointName, String newCustomCompassPointName){
        Block targetBlock = player.getTargetBlockExact(20, FluidCollisionMode.ALWAYS);

        if (targetBlock == null) {
            player.sendMessage(
                    ChatColor.RED + "No point has been set. Try moving closer to the point you want to set it to.");
            return false;
        }
        if (newCustomCompassPointName == null)
            plugin.getConfig().set("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPointName, targetBlock.getLocation());
        else{
            Location location = plugin.getConfig().getLocation("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPointName);
            plugin.getConfig().set("Users." + player.getUniqueId() + ".CustomCompassPoints." + newCustomCompassPointName, location);
            plugin.getConfig().set("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPointName, null);
        }

        plugin.saveConfig();
        return true;
    }
}
