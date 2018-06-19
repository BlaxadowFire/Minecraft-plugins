package net.ddns.blaxadowfire.minion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable()
    {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Minion Plugin is loading");
        this.getServer().getPluginManager().registerEvents(new SpawnEntity(), this);
    }
}
