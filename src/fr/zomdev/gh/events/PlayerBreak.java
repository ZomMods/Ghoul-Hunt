package fr.zomdev.gh.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static fr.zomdev.gh.Main.pList;

/**
 * Created by ZomDev on 01/09/2016.
 */
public class PlayerBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(pList.contains(p.getUniqueId())){

            if(e.getBlock().getType() == Material.IRON_BLOCK){

            }else{
                e.setCancelled(true);
            }
        }
    }
}
