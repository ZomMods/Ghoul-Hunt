package fr.zomdev.gh.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.BOLD;

/**
 * Created by ZomDev on 03/09/2016.
 */
public class GhoulKit {

    public static ItemStack quinque = new ItemStack(Material.STICK);

    private static ItemStack ghoulHelmet = new ItemStack(Material.SKULL_ITEM);
    private static ItemStack ghoulChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
    private static ItemStack ghoulLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
    private static ItemStack ghoulBoots = new ItemStack(Material.LEATHER_BOOTS);

    public static ItemStack[] ghoulKit = {ghoulHelmet, ghoulChestPlate, ghoulLeggings, ghoulBoots};

    public static void registerItems(){

        ItemMeta quinqueMeta = quinque.getItemMeta();
        quinqueMeta.setDisplayName(BOLD + "§aQUINQUE");
        quinque.setItemMeta(quinqueMeta);

        ItemMeta ghoulHelmetMeta = ghoulHelmet.getItemMeta();
        ghoulHelmetMeta.setDisplayName(BOLD + "§cGHOUL HEAD");
        ghoulHelmet.setItemMeta(ghoulHelmetMeta);

        ItemMeta ghoulChestPlateMeta = ghoulChestPlate.getItemMeta();
        ghoulChestPlateMeta.setDisplayName(BOLD + "§cGHOUL SKIN");
        ghoulChestPlate.setItemMeta(ghoulChestPlateMeta);

        ItemMeta ghoulLeggingsMeta = ghoulLeggings.getItemMeta();
        ghoulLeggingsMeta.setDisplayName(BOLD + "§cGHOUL SKIN");
        ghoulLeggings.setItemMeta(ghoulLeggingsMeta);

        ItemMeta ghoulBootsMeta = ghoulBoots.getItemMeta();
        ghoulBootsMeta.setDisplayName(BOLD + "§cGHOUL SKIN");
        ghoulBoots.setItemMeta(ghoulBootsMeta);
    }
}
