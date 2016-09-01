package fr.zomdev.gh.timers;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;

import java.util.UUID;

import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;
import static org.bukkit.ChatColor.*;

/**
 * Created by ZomDev on 31/08/2016.
 */
public class PreGame {

    public static int timer = 30;
    public static int task;

    public static void launchPreGameTimer() {

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {

                if(timer == 30){

                    for (UUID id : pList){

                        Bukkit.getPlayer(id).sendMessage(prefix + YELLOW + "Game is about to start !");

                    }
                }

                timer--;

                if (timer <= 15) {

                    for (UUID id : pList) {

                        Bukkit.getPlayer(id).sendMessage(prefix + GREEN + "Game is about to begin in " + timer + "s !");

                    }
                }

                if (timer == 0) {

                    for (UUID id : pList) {

                        Bukkit.getPlayer(id).sendMessage(prefix + BOLD + RED + "Game Starts !");

                        GameState.setState(GameState.GAME);

                        Bukkit.getScheduler().cancelTask(task);

                    }
                }
            }

        }, 0, 20);
    }
}
