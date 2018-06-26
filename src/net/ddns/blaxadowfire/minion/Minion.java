package net.ddns.blaxadowfire.minion;

import com.google.common.base.Verify;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Minion {

    private Villager villager;

    public Minion(Location loc, UUID uuid){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        SkullMeta sm = (SkullMeta) skull.getItemMeta();

        sm.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        skull.setItemMeta(sm);

        villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.getEquipment().setHelmet(skull);
        villager.setAI(false);
        villager.setGlowing(true);
        villager.setBaby();
    }

    public void Mining(){
        villager.getTargetBlock(null, 5).breakNaturally();
    }

}
