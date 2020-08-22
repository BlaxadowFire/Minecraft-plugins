package me.BlaxadowFire.HelloWorld;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ClearWeather implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void WeatherClear (WeatherChangeEvent e)
    {
        if (e.toWeatherState())
        {
            e.setCancelled(true);

            Bukkit.broadcastMessage("WEATHER CHANGE BLOCKED");
        }
    }
}
