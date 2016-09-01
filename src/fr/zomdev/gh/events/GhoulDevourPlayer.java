package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import static fr.zomdev.gh.Main.ghouls;
import static fr.zomdev.gh.Main.pList;
import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.RED;


/**
 * Created by ZomDev on 01/09/2016.
 */
public class GhoulDevourPlayer implements Listener {

    @EventHandler
    public void onDevour(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        Entity ent = e.getRightClicked();
        if (ent instanceof Player) {
            Player target = (Player) ent;
            if (pList.contains(target.getUniqueId()) && target.getGameMode() == GameMode.SURVIVAL && ghouls.contains(p.getUniqueId())) {
                getDevoured(target);
            }
        }
    }

    public static int task;
    public static int timer = 15;

    public static void getDevoured(Player target) {



        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {

                timer--;

                if (timer == 0) {
                    target.setGameMode(GameMode.SPECTATOR);
                    target.sendMessage(BOLD + "" + RED + "YOU'RE DEAD!");

                }
            }

        }, 0, 20);
    }
}
