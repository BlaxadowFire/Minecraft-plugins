package tk.blaxadowfire.minecraft.testserver;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tk.blaxadowfire.minecraft.testserver.commands.FeedCommand;
import tk.blaxadowfire.minecraft.testserver.commands.GameModeCommand;
import tk.blaxadowfire.minecraft.testserver.commands.ClearReloadCommand;
import tk.blaxadowfire.minecraft.testserver.commands.HealCommand;
import tk.blaxadowfire.minecraft.testserver.events.EventsClass;

public class TestServerPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "TestServerPlugin has started");
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getCommand("gm").setExecutor(new GameModeCommand());
        getCommand("clearreload").setExecutor(new ClearReloadCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        loadConfig();
    }
    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
