package fr.zomdev.gh.timers;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.utils.GameState;
import fr.zomdev.gh.utils.Locations;
import org.bukkit.Bukkit;

import java.util.Random;
import java.util.UUID;

import static fr.zomdev.gh.Main.*;
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

                        checkGhoul();

                        Bukkit.getScheduler().cancelTask(task);

                    }
                }
            }

        }, 0, 20);
    }


    private static void checkGhoul(){
        if(ghouls.size() == 0){
            int r = new Random().nextInt(pList.size());

            UUID ghoul = pList.get(r);

            pList.remove(ghoul);

            ghouls.add(ghoul);

            Bukkit.getPlayer(ghoul).sendMessage(prefix + RED + "You're the ghoul! You must devour the humans!");
            Bukkit.getPlayer(ghoul).teleport(Locations.GHOUL_SPAWN);

            for (UUID id : pList){
                Bukkit.getPlayer(id).sendMessage(prefix + RED + Bukkit.getPlayer(ghoul).getName() + " is now the ghoul!");
                Bukkit.getPlayer(id).teleport(Locations.PLAYER_SPAWN);
            }
        }
    }
}
