package net.ddns.blaxadowfire.minion;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Minion {
    private Villager villager;
    private MinionTask _minionTask;
    private int _taskId = 0;

    public Minion(Location loc, UUID uuid, MinionTask task){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        SkullMeta sm = (SkullMeta) skull.getItemMeta();

        sm.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        skull.setItemMeta(sm);

        villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.getEquipment().setHelmet(skull);
        villager.getEquipment().setItemInMainHand(new ItemStack(Material.WOOD_PICKAXE));
        villager.setAI(false);
        villager.setGlowing(true);
        villager.setBaby();
        villager.setCustomName(Bukkit.getOfflinePlayer(uuid).getName() + "'s minion");
        villager.setCustomNameVisible(true);

        _minionTask = task;

        _taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinionPlugin.getPlugin(), this::RunTask, 0, 20L);
    }

    public void RunTask(){
        if(villager.isDead())  {
            Bukkit.getScheduler().cancelTask(_taskId);
            return;
        }
        switch (_minionTask){
            case MINING:
                Mine();
                break;
            case ATTACKING:
                Attack();
                break;
        }
    }

    public void Mine(){
        villager.getTargetBlock(null, 5).breakNaturally(new ItemStack(Material.WOOD_PICKAXE));
    }

    public void Attack(){
        LivingEntity entity = villager.getTarget();
        if(entity == null)
            Bukkit.broadcastMessage(":(");
        entity.damage(1);
    }
    
}
