package tk.blaxadowfire.minecraft.deathnote.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import tk.blaxadowfire.minecraft.deathnote.DeathNotePlugin;
import tk.blaxadowfire.minecraft.deathnote.items.DeathNote;

import java.util.List;

public class DeathNoteCommand implements TabExecutor {
    private Plugin plugin = DeathNotePlugin.getPlugin(DeathNotePlugin.class);
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can execute ths command");
            return true;
        }

        Player player = (Player) commandSender;
        ItemStack deathNote = new DeathNote().getDeathNote();
        player.getInventory().addItem(deathNote);

        Bukkit.getServer().broadcastMessage(Bukkit.getServer().getWorld("world").getFullTime()/24000 + "");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return null;
    }
}
