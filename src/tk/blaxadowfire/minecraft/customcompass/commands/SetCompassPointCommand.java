package tk.blaxadowfire.minecraft.customcompass.commands;

import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetCompassPointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if ( commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            ItemStack mainItem = player.getInventory().getItemInMainHand();
            ItemStack offItem = player.getInventory().getItemInOffHand();
            if (mainItem.getType().equals(Material.COMPASS) || offItem.getType().equals(Material.COMPASS)) {
                Block targetBlock = player.getTargetBlockExact(20, FluidCollisionMode.ALWAYS);
                if (targetBlock != null) {
                    player.setCompassTarget(targetBlock.getLocation());
                    commandSender.sendMessage("Compass location point set.");
                }
                else{
                    commandSender.sendMessage(
                            ChatColor.RED + "No point has been set. Try moving closer to the point you want to set it to.");
                }
            }
            else {
                commandSender.sendMessage(
                        ChatColor.RED + "You need to have a compass in your hand to execute this command.");
            }
        }
        return true;
    }
}
