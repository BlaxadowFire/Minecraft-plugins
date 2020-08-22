package tk.blaxadowfire.minecraft.deathnote.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class DeathNote {

    private ItemStack deathNote;

    public ItemStack getDeathNote(){
        return deathNote;
    }
    public void setDeathNote(ItemStack deathNote){
        this.deathNote = deathNote;
    }

    public DeathNote(){
        GenerateBook();
    }
    private void GenerateBook(){
        deathNote = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bm = (BookMeta) deathNote.getItemMeta();
        bm.setTitle("DeathNote");
        bm.setDisplayName("DeathNote");
        bm = SetRules(bm);
        deathNote.setItemMeta(bm);
    }
    private BookMeta SetRules(BookMeta bm){
        List<String> pages = new ArrayList<>();

        pages.add("DEATH NOTE\n" +
                "HOW TO USE IT\n" +
                ChatColor.RED + "DO NOT EDIT THIS PAGE\n" +
                "DO NOT SELECT TEXT, AS THIS MAY CRASH YOUR CLIENT\n" +
                "Link is case sensitive!\n" +
                ChatColor.BLUE + "https://bit.ly/DnRules\n" +
                ChatColor.BLACK + "The above link brings you to the deathnote rules.\n" +
                ChatColor.DARK_GRAY + "example:\n" +
                ChatColor.BLUE + "[player:playername]\n[day:minecraftday]\n[cause:causeofdeath]\n");

        pages.add(ChatColor.RED + "DO NOT EDIT THIS PAGE\n" +
                ChatColor.DARK_GREEN + "Manual:\n" +
                ChatColor.BLACK + "1 person per page\n" +
                ChatColor.DARK_GREEN + "playername: " +
                ChatColor.BLACK + "the name of the player\n" +
                ChatColor.DARK_GREEN + "minecraftday: " +
                ChatColor.BLACK + "the day located next to the local difficulty\n" +
                ChatColor.DARK_GREEN + "causeofdeath: " +
                ChatColor.BLACK + "the cause of death\n");

        bm.setPages(pages);
        return bm;
    }
}
