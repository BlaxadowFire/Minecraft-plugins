package me.BlaxadowFire.HelloWorld;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Shunhui_Anvil implements Listener {

    @EventHandler
    public void DenyAnvil(PlayerInteractEvent e)
    {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.ANVIL))
        {
            if (!e.getPlayer().hasPermission("anvil.use"))
            {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.DARK_RED + "Shunhui" + ChatColor.GREEN + " zuigt piemels");
            }
        }
    }
}
