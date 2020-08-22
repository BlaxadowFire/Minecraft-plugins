package me.BlaxadowFIre.DisableDoor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableDoorMain extends JavaPlugin {

    @Override
    public void onEnable()
    {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "DisableDoor Enabled");
        this.getServer().getPluginManager().registerEvents(new Disable(), this);
    }
}
