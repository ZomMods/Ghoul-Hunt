package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import static fr.zomdev.gh.Main.*;
import static fr.zomdev.gh.timers.Finish.launchFinish;
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

    private static void freeze(Player ghoul, Player target){
        devoured.add(target.getUniqueId());
        frozen.add(ghoul.getUniqueId());
    }

    public static int task;
    public static int timer = 15;

    public static void getDevoured(Player target) {
        freeze(Bukkit.getPlayer(ghouls.get(0)), target);

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {
                timer--;

                if (timer > 0) {
                    target.setLevel(timer);

                    Bukkit.getPlayer(ghouls.get(0)).setLevel(timer);

                }



                if (timer == 0) {
                    target.setLevel(timer);

                    target.setGameMode(GameMode.SPECTATOR);

                    target.sendMessage(BOLD + "" + RED + "YOU'RE DEAD!");

                    devoured.clear();

                    deads.add(target.getUniqueId());

                    frozen.clear();

                    if(pList.size() == 0 ){

                        Bukkit.broadcastMessage(prefix + RED + Bukkit.getPlayer(ghouls.get(0)).getName() + " the ghoul has win the game!");

                        Bukkit.broadcastMessage(prefix + RED + "The Server if going to restart");

                        GameState.setState(GameState.FINISH);

                        launchFinish();

                        Bukkit.getScheduler().cancelTask(task);
                    }
                }
            }

        }, 0, 20);
    }


}
