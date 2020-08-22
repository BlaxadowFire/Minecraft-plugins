package tk.blaxadowfire.minecraft.deathnote.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tk.blaxadowfire.minecraft.deathnote.DeathNotePlugin;

public class DieCommand implements CommandExecutor {
    Plugin plugin = DeathNotePlugin.getPlugin(DeathNotePlugin.class);
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length < 2)
            return false;

        Boolean playerFound = false;

        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            if (!player.getName().equals(args[0]))
                continue;
            plugin.getConfig().set("DeathNote.Targets.UUIDs." + player.getUniqueId() + ".Day", Integer.parseInt(args[1]));
            if (args.length == 3)
                plugin.getConfig().set("DeathNote.Targets.UUIDs." + player.getUniqueId() + ".Cause", args[2]);
            playerFound = true;
            break;
        }
        if (!playerFound){
            plugin.getConfig().set("DeathNote.Targets.Names." + args[0] + ".Day", Integer.parseInt(args[1]));
            plugin.getConfig().set("DeathNote.Targets.Names." + args[0] + ".Cause", args[2]);
        }

        plugin.saveConfig();
        return true;
    }
}
