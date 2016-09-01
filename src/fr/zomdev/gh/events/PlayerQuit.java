package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.timers.PreGame;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;
import static fr.zomdev.gh.timers.PreGame.timer;
import static org.bukkit.ChatColor.RED;

/**
 * Created by ZomDev on 30/08/2016.
 */
public class PlayerQuit implements Listener {

    private FileConfiguration config = Main.getInstance().getConfig();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (pList.contains(p.getUniqueId())) {

            pList.remove(p.getUniqueId());

            for (UUID id : pList) {

                Bukkit.getPlayer(id).sendMessage(prefix + ChatColor.GOLD + p.getName() + " has left the game !");

            }
            if (pList.size() < config.getInt("MaxPlayers") / 2 && GameState.isState(GameState.PREGAME)) {

                Bukkit.getScheduler().cancelTask(PreGame.task);

                timer = 30;

                for (UUID id : pList) {

                    Bukkit.getPlayer(id).sendMessage(prefix + RED + "There are not enough players to start the game !");

                }

                GameState.setState(GameState.LOBBY);

            }

        } else {

        }
    }
}
