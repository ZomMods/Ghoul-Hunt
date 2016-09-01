package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static fr.zomdev.gh.Main.pList;

/**
 * Created by ZomDev on 01/09/2016.
 */
public class PlayerBreak implements Listener {

    private FileConfiguration config = Main.getInstance().getConfig();

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(pList.contains(p.getUniqueId())){

            if(e.getBlock().getType() == Material.getMaterial(config.getString("QuinqueBlock"))){

            }else{
                e.setCancelled(true);
            }
        }
    }
}
