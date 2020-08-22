package tk.blaxadowfire.minecraft.customcompass.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import tk.blaxadowfire.minecraft.customcompass.CustomCompassPlugin;

import java.util.ArrayList;
import java.util.List;

public class EventsClass implements Listener {
    private Plugin plugin = CustomCompassPlugin.getPlugin(CustomCompassPlugin.class);

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent playerInteractEvent){
        Player player = playerInteractEvent.getPlayer();

        if (playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if (player.getInventory().getItemInMainHand().getType() != Material.COMPASS)
            return;

        //Get all points from config
        List<String> customCompassPointNames = new ArrayList<>();
        customCompassPointNames.addAll(plugin.getConfig().getConfigurationSection("Users." + player.getUniqueId() + ".CustomCompassPoints").getKeys(false));
        if (customCompassPointNames.size() == 0)
            return;
        List<Location> customCompassPoints = new ArrayList<>();
        for (String customCompassPointName : customCompassPointNames)
            customCompassPoints.add(plugin.getConfig().getLocation("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPointName));

        //get amount of points
        //math.ceil the amount of points to a multiple of 9 (max 54)
        int amountOfItemSlots = (int) (Math.ceil((double) customCompassPointNames.size() / 9) * 9) ;

        Inventory customCompassPointsInventory = plugin.getServer().createInventory(null, amountOfItemSlots, ChatColor.GREEN + "Custom Compass Points");

        //fill every inventory slot with a compass (depending on the amount of points) (with a for loop and a counting integer)
        //give every compass the corresponding name to the point name
        for (String customCompassPointName : customCompassPointNames) {
            int index = customCompassPointNames.indexOf(customCompassPointName);
            ItemStack item = new ItemStack(Material.COMPASS, 1);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(customCompassPointName);
            item.setItemMeta(itemMeta);
            customCompassPointsInventory.setItem(index,item);
        }

        player.openInventory(customCompassPointsInventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent){
        Player player = (Player) inventoryClickEvent.getWhoClicked();


        ClickType click = inventoryClickEvent.getClick();
        ItemStack item = inventoryClickEvent.getCurrentItem();
        if (!player.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Custom Compass Points"))
            return;

        if (inventoryClickEvent.getAction().equals(InventoryAction.HOTBAR_SWAP))
            inventoryClickEvent.setCancelled(true); //IS REQUIRED FOR BUG

        String customCompassPointName = item.getItemMeta().getDisplayName();
        inventoryClickEvent.setCancelled(true);
        inventoryClickEvent.setResult(Event.Result.DENY);
        player.closeInventory();
        Location location = plugin.getConfig().getLocation("Users." + player.getUniqueId() + ".CustomCompassPoints." + customCompassPointName);
        if (location == null)
            return;
        player.setCompassTarget(location);
        player.sendMessage(ChatColor.GOLD + "Point " + ChatColor.GREEN + customCompassPointName + ChatColor.GOLD + " has been set.");
    }
}







//    @EventHandler
//    public void OnPlayerQuit(PlayerQuitEvent playerQuitEvent) {
//        Disconnect(playerQuitEvent);
//    }
//
//    @EventHandler
//    public void OnPlayerKick(PlayerKickEvent playerKickEvent) {
//        Disconnect(playerKickEvent);
//    }
//
//    @EventHandler
//    public void OnPlayerJoin(PlayerJoinEvent playerJoinEvent) {
//        Player player = playerJoinEvent.getPlayer();
//        Bukkit.getScheduler().runTask(plugin, () ->
//                player.setCompassTarget(plugin.getConfig().getLocation("Users." + player.getUniqueId() + ".CustomCompassPoint")));
//    }
//
//    public void Disconnect(PlayerEvent playerEvent) {
//        Player player = playerEvent.getPlayer();
//        Location location = player.getCompassTarget();
//        plugin.getConfig().set("Users." + player.getUniqueId() + ".CustomCompassPoint", location);
//        plugin.saveConfig();
//    }