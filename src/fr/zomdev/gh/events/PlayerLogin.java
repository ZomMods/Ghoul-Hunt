package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import static fr.zomdev.gh.Main.pList;

/**
 * Created by ZomDev on 31/08/2016.
 */
public class PlayerLogin implements Listener{

    private FileConfiguration config = Main.getInstance().getConfig();

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        if(pList.size() >= config.getInt("MaxPlayers")){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cGame is full !");
        }else if (GameState.isState(GameState.FINISH)){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cServer is restarting");
        }else{
            e.allow();
        }
    }
}
