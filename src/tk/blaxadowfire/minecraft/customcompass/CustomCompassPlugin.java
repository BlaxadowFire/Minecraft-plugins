package tk.blaxadowfire.minecraft.customcompass;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tk.blaxadowfire.minecraft.customcompass.commands.CustomCompassPointCommand;
import tk.blaxadowfire.minecraft.customcompass.events.EventsClass;

public class CustomCompassPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "CustomCompass has started");
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        //getCommand("SetCompassPoint").setExecutor(new SetCompassPointCommand());
        getCommand("CustomCompassPoint").setExecutor(new CustomCompassPointCommand());
        loadConfig();
    }
    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
