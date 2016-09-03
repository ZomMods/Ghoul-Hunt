package fr.zomdev.gh.timers;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.kits.GhoulKit;
import fr.zomdev.gh.utils.GameState;
import fr.zomdev.gh.utils.Locations;
import org.bukkit.*;
import org.bukkit.entity.Player;

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

                if (timer == 30) {

                    for (UUID id : pList) {

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

                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.getInventory().clear();
                        }

                        Bukkit.getPlayer(id).setGameMode(GameMode.SURVIVAL);

                        GameState.setState(GameState.GAME);

                        checkGhoul();

                        Bukkit.getPlayer(ghouls.get(0)).setGameMode(GameMode.SURVIVAL);

                        Bukkit.getScheduler().cancelTask(task);

                    }
                }
            }

        }, 0, 20);
    }


    private static void checkGhoul() {
        if (ghouls.size() == 0) {
            int r = new Random().nextInt(pList.size());

            UUID ghoul = pList.get(r);

            Player g = Bukkit.getPlayer(ghoul);

            pList.remove(ghoul);

            ghouls.add(ghoul);

            g.sendMessage(prefix + RED + BOLD + "You're the ghoul! You must devour the humans!");

            g.getInventory().setHelmet(GhoulKit.ghoulKit[0]);
            g.getInventory().setChestplate(GhoulKit.ghoulKit[1]);
            g.getInventory().setLeggings(GhoulKit.ghoulKit[2]);
            g.getInventory().setBoots(GhoulKit.ghoulKit[3]);

            try {

                g.teleport(Locations.getGhoulSpawn());
                Bukkit.getWorld(g.getWorld().getName()).playEffect(g.getLocation(), Effect.STEP_SOUND, Material.OBSIDIAN);
                Bukkit.getWorld(g.getWorld().getName()).playSound(g.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);

            } catch (Exception ex) {
                ex.printStackTrace();

            }


            for (UUID id : pList) {

                Bukkit.getPlayer(id).sendMessage(prefix + RED + g.getName() + " is now the ghoul!");

                try {

                    Bukkit.getPlayer(id).teleport(Locations.getPlayerSpawn());

                } catch (NullPointerException ex) {
                    ex.printStackTrace();

                }

            }
        }
    }
}
