package fr.zomdev.gh.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;

/**
 * Created by Thomas on 30/08/2016.
 */
public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(pList.contains(p.getUniqueId())){
            pList.remove(p.getUniqueId());
            Bukkit.broadcastMessage(prefix + ChatColor.GOLD  + p.getName() + " has left the game !");
        }else{
            return;
        }
    }
}
