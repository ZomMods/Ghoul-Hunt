package fr.zomdev.gh.events;

import fr.zomdev.gh.utils.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.zomdev.gh.Main.config;
import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;
import static fr.zomdev.gh.timers.PreGame.launchPreGameTimer;

/**
 * Created by Thomas on 30/08/2016.
 */
public class PlayerJoin implements Listener {

    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(pList.size() < config.getInt("MaxPlayers") / 2 ) {
            e.setJoinMessage(prefix + p.getName() + " has joined the game !");
            pList.add(p.getUniqueId());
            if(pList.size() >= config.getInt("MaxPlayers") / 2){
                GameState.setState(GameState.PREGAME);
                launchPreGameTimer();
            }
        }else{
            return;
        }
    }
}
