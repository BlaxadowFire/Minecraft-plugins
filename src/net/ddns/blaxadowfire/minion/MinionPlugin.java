package net.ddns.blaxadowfire.minion;

import net.ddns.blaxadowfire.minion.commands.MinionCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MinionPlugin extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable()
    {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Minion Plugin is loading");

        getCommand("minion").setExecutor(new MinionCommand());

        instance = this;
    }

    public static Plugin getPlugin(){
        return instance;
    }
}
