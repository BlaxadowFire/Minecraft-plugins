package me.BlaxadowFire.HelloWorld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class IceBlockStuff implements Listener {

   public  ItemStack itemIce = new ItemStack(Material.ICE,4);
   public  ItemStack itemPackedIceNormal = new ItemStack(Material.PACKED_ICE,1);
   public  ItemStack itemPackedIce = new ItemStack(Material.PACKED_ICE,1);

   public String ItemPackedIceName = ChatColor.AQUA + "" + ChatColor.MAGIC + "1" + ChatColor.RED + "The Amazing Packed Ice" + ChatColor.AQUA + "" + ChatColor.MAGIC + "1";

   private Plugin plugin = HelloWorldMain.getPlugin(HelloWorldMain.class);

   public void PackedIce()
   {
       ItemMeta meta = itemPackedIce.getItemMeta();

       meta.setDisplayName(ItemPackedIceName);
       ArrayList<String> lore = new ArrayList<String>();
       lore.add(ChatColor.WHITE + "The most amazing block of packed ice ever created!");
       meta.setLore(lore);

       meta.setUnbreakable(true);
       //meta.addEnchant(Enchantment.DURABILITY, 1, true);

       itemPackedIce.setItemMeta(meta);
   }

    public void Ice()
    {
        ItemMeta meta = itemIce.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.MAGIC + "1" + ChatColor.RED + "Amazing Ice" + ChatColor.AQUA + "" + ChatColor.MAGIC + "1");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Amazing ICE!");
        meta.setLore(lore);

        itemIce.setItemMeta(meta);
    }


    public void CustomRecipe(){
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(plugin, "r"), itemPackedIce);

        r.shape("%% ","%% ","   ");
        r.setIngredient('%', Material.ICE);

        plugin.getServer().addRecipe(r);

        ShapedRecipe t = new ShapedRecipe(new NamespacedKey(plugin, "t"), itemIce);


        t.shape("   "," % ","   ");
        t.setIngredient('%', Material.PACKED_ICE);

        plugin.getServer().addRecipe(t);


        ShapedRecipe e = new ShapedRecipe(new NamespacedKey(plugin, "e"), itemPackedIceNormal);

        e.shape("   ","%  ","   ");
        e.setIngredient('%', Material.PACKED_ICE);
        plugin.getServer().addRecipe(e);
    }



    @EventHandler
    public void PackedIceUncraftCheck(PrepareItemCraftEvent e)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Start");
        if (e.getInventory().getResult().getType() == Material.PACKED_ICE) //checks if final result would be equal to packed ice
        {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Result=PackedIce");
            for (int i = 1; i < e.getInventory().getSize();i++) //checks every slot in the inventory
            {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Going through slot: " + i);
                final ItemStack item  = e.getInventory().getItem(i);
                if (e.getInventory().getItem(i) != null) // checks if current slot isn't empty
                {
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Slot is: " + item.getType());
                    if(item.getType() == Material.PACKED_ICE) // checks if current slot is packed ice
                    {
                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Slot contains PackedIce");
                        if (item.getItemMeta().isUnbreakable()) {
                            e.getInventory().setResult(new ItemStack(Material.PACKED_ICE, 5));
                            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "THIS IS IT");
                        }
                        else
                        {
                            e.getInventory().setResult(new ItemStack(Material.AIR,1));
                        }
                    }
                }
            }
        }
    }

}