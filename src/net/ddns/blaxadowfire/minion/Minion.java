package net.ddns.blaxadowfire.minion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Minion {
    private Villager minion;
    private MinionTask _minionTask;
    private int _taskId = 0;
    private UUID uuid;

    public Minion(Location loc, UUID uuid1, MinionTask task){
        uuid = uuid1;
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid).getPlayer());
        skull.setItemMeta(skullmeta);

        minion = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        minion.getEquipment().setHelmet(skull);
        minion.getEquipment().setItemInMainHand(new ItemStack(Material.WOOD_PICKAXE));
        minion.setAI(false);
        minion.setGravity(true);
        minion.setGlowing(true);
        minion.setBaby();

        minion.setCustomNameVisible(true);
        minion.setCanPickupItems(true);
        minion.setInvulnerable(true);

        _minionTask = task;

        _taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinionPlugin.getPlugin(), this::RunTask, 0, 20L);

        minion.setCustomName(Bukkit.getOfflinePlayer(uuid).getName() + "'s minion: " + _taskId);
    }

    public void RunTask(){
        if(minion.isDead())  {
            Bukkit.getScheduler().cancelTask(_taskId);
            return;
        }
        switch (_minionTask){
            case MINE:
                Mine();
                break;
            case ATTACK:
                Attack();
                break;
            case FOLLOW:
                Follow();
                break;
        }
    }

    public void Mine(){
        minion.getTargetBlock(null, 2).breakNaturally(new ItemStack(Material.WOOD_PICKAXE));
    }

    public void Attack(){
        LivingEntity entity = minion.getTarget();
        if(entity == null)
            Bukkit.broadcastMessage(":(");
        entity.damage(1);
    }
    public void Follow(){
        minion.setAI(true);
    }
    
}
