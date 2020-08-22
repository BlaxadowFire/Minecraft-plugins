package me.BlaxadowFire.HelloWorld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldMain extends JavaPlugin {

    @Override
    public void onEnable(){
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Shunhui = Gay");
        this.getServer().getPluginManager().registerEvents(new Shunhui_Anvil(), this);
        //this.getServer().getPluginManager().registerEvents(new ClearWeather(), this);
        this.getServer().getPluginManager().registerEvents(new IceBlockStuff(), this);

        IceBlockStuff item = new IceBlockStuff();
        item.PackedIce();
        item.Ice();
        item.CustomRecipe();
    }
}
