package fr.zomdev.gh.events;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.material.Redstone;

import java.util.UUID;

import static fr.zomdev.gh.Main.*;
import static fr.zomdev.gh.timers.Finish.launchFinishGhoul;
import static fr.zomdev.gh.utils.ConfigUtil.getLocConfig;
import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.RED;


/**
 * Created by ZomDev on 01/09/2016.
 */
public class GhoulDevourPlayer implements Listener {

    static boolean canDevour = true;

    @EventHandler
    public void onDevour(PlayerInteractEntityEvent e) {

        Player p = e.getPlayer();
        Entity ent = e.getRightClicked();


        if (ent instanceof Player) {

            Player target = (Player) ent;

            if (pList.contains(target.getUniqueId()) && target.getGameMode() == GameMode.SURVIVAL && ghouls.contains(p.getUniqueId())) {
                if (canDevour == true) {
                    freeze(Bukkit.getPlayer(ghouls.get(0)), target);
                    getDevoured(target);
                }else{
                    p.sendMessage("" + RED + BOLD + "You can only devour a human once at the same time!");
                }
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
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {

                if(timer == 15){
                    canDevour = false;
                }

                timer --;

                if (timer > 0) {
                    target.setLevel(timer);
                    Bukkit.getWorld(getLocConfig().getString("PlayerSpawn.WorldName")).playSound(target.getLocation(), Sound.ENTITY_CAT_HURT, 1,2);
                    Bukkit.getPlayer(ghouls.get(0)).setLevel(timer);
                    Bukkit.getWorld(getLocConfig().getString("PlayerSpawn.WorldName")).playEffect(target.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);

                }



                if (timer == 0) {
                    target.setLevel(timer);

                    target.setGameMode(GameMode.SPECTATOR);

                    target.sendMessage(BOLD + "" + RED + "YOU'RE DEAD!");

                    for(UUID id : pList){
                        Bukkit.getPlayer(id).sendMessage(GOLD + target.getName() + RED + BOLD + " has been killed!");
                    }

                    canDevour = true;

                    devoured.clear();

                    deads.add(target.getUniqueId());

                    frozen.clear();

                    pList.remove(target.getUniqueId());

                    if(pList.size() == 0 ){

                        Bukkit.broadcastMessage(prefix + RED + Bukkit.getPlayer(ghouls.get(0)).getName() + " the ghoul has win the game!");

                        Bukkit.broadcastMessage(prefix + RED + "The Server is going to restart");

                        GameState.setState(GameState.FINISH);

                        launchFinishGhoul();

                        Bukkit.getScheduler().cancelTask(task);
                    }

                    Bukkit.getScheduler().cancelTask(task);
                }
            }

        }, 0, 20);
    }


}
