package tk.blaxadowfire.minecraft.deathnote;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tk.blaxadowfire.minecraft.deathnote.commands.DeathNoteCommand;
import tk.blaxadowfire.minecraft.deathnote.commands.DieCommand;
import tk.blaxadowfire.minecraft.deathnote.events.EventsClass;

public class DeathNotePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "DeathNote has started");
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getCommand("deathnote").setExecutor(new DeathNoteCommand());
        getCommand("die").setExecutor(new DieCommand());
        loadConfig();
    }
    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}

//TODO
//fill config with targeted users
//every time a player logs in, check if it's name is in the deathbook (config)
//if the name is in the deathbook (config), create a new entry with the uuid of the player
//kill the user on the day when the day is reached (onNewDayEvent?)
//create custom kill options