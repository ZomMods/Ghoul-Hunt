package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.utils.ConfigUtil;
import fr.zomdev.gh.utils.GameState;
import fr.zomdev.gh.utils.Locations;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;
import static fr.zomdev.gh.timers.PreGame.launchPreGameTimer;

/**
 * Created by ZomDev on 30/08/2016.
 */
public class PlayerJoin implements Listener {

    public static FileConfiguration config = Main.getInstance().getConfig();

    private static FileConfiguration locs = ConfigUtil.getLocConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);

        if (GameState.isState(GameState.LOBBY)) {

            if (pList.size() < config.getInt("MaxPlayers")) {

                e.setJoinMessage(prefix + p.getName() + " has joined the game !");

                pList.add(p.getUniqueId());

                p.setInvulnerable(true);
                p.setHealth(p.getMaxHealth());
                p.setFoodLevel(20);
                p.setSaturation(1000.0f);
                p.setLevel(0);
                p.setGameMode(GameMode.SURVIVAL);

                try {

                    p.teleport(Locations.getLOBBY());

                }catch(Exception ex){
                    ex.printStackTrace();
                }

                if (pList.size() >= config.getInt("MaxPlayers") / 2) {

                    GameState.setState(GameState.PREGAME);

                    launchPreGameTimer();
                }
            } else {

            }
        }
    }
}
