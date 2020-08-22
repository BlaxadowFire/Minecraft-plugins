package me.BlaxadowFIre.DisableDoor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Disable implements Listener {

    @EventHandler
    public void DisableDoor(PlayerInteractEvent e)
    {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.WOODEN_DOOR))
        {
            e.setCancelled(true);
        }
    }
}
