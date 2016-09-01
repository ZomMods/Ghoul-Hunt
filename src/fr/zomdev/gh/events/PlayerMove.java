package fr.zomdev.gh.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static fr.zomdev.gh.Main.frozen;

/**
 * Created by ZomDev on 01/09/2016.
 */
public class PlayerMove implements Listener{

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(frozen.contains(p.getUniqueId())){
            e.setCancelled(true);
        }
    }
}
