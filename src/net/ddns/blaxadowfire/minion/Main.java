package net.ddns.blaxadowfire.minion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable()
    {
        getCommand("monster").setExecutor(new SpawnEntity(this));
        getCommand("givesword").setExecutor(new SpawnEntity(this));
        getCommand("givearmor").setExecutor(new SpawnEntity(this));
    }
}
