package fr.zomdev.gh.timers;

import fr.zomdev.gh.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by ZomDev on 01/09/2016.
 */
public class Finish {

    public static int task;
    private static int timer = 10;

    public static void launchFinish() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.kickPlayer("Server is restarting !");
        }

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {
                timer--;

                if (timer == 0) {
                    Bukkit.getServer().reload();

                }
            }

        }, 0, 20);
    }

}
