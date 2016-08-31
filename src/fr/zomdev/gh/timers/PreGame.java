package fr.zomdev.gh.timers;

import fr.zomdev.gh.Main;
import org.bukkit.Bukkit;

import java.util.UUID;

import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;
import static org.bukkit.ChatColor.GREEN;

/**
 * Created by Thomas on 31/08/2016.
 */
public class PreGame {

    static int timer = 30;

    public static void launchPreGameTimer(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                timer--;

                for(UUID id : pList){
                    Bukkit.getPlayer(id).sendMessage(prefix + GREEN + "Game is about to begin in " + timer + "s !");
                }
            }

        },0,20);
    }
}
