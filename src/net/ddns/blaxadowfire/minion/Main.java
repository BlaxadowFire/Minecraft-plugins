package net.ddns.blaxadowfire.minion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable()
    {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Minion Plugin is loading");
        //this.getServer().getPluginManager().registerEvents(new Minion(), this);

        getCommand("mine").setExecutor(new SpawnEntity(this));
        getCommand("minion").setExecutor(new SpawnEntity(this));
        getCommand("monster").setExecutor(new SpawnEntity(this));
        getCommand("givesword").setExecutor(new SpawnEntity(this));
        getCommand("givearmor").setExecutor(new SpawnEntity(this));
    }
}
