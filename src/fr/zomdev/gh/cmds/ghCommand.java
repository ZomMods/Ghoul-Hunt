package fr.zomdev.gh.cmds;

import fr.zomdev.gh.Main;
import fr.zomdev.gh.timers.PreGame;
import fr.zomdev.gh.utils.ConfigUtil;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.UUID;

import static fr.zomdev.gh.Main.pList;
import static fr.zomdev.gh.Main.prefix;
import static fr.zomdev.gh.events.PlayerJoin.config;
import static fr.zomdev.gh.timers.PreGame.timer;
import static fr.zomdev.gh.utils.ConfigUtil.saveConfigs;
import static org.bukkit.ChatColor.RED;

/**
 * Created by ZomDev on 01/09/2016.
 */
public class ghCommand implements CommandExecutor {

    private static FileConfiguration locs = ConfigUtil.getLocConfig();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        Main.getInstance().getCommand("ghoulhunt").tabComplete(p, "gh", args);

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou must be a player to send this command !");

        } else {


            if (args.length == 0) {
                p.sendMessage(prefix + RED + "You must use an argument!");

            } else if (args.length == 1) {

                if (p.hasPermission(new Permission("ghoulhunt.setLocations"))) {

                    if (args[0].equalsIgnoreCase("setlobby")) {
                        setLocation("Lobby", p.getLocation());
                        saveConfigs();

                        p.sendMessage(prefix + "You've set the lobby's location");

                    } else if (args[0].equalsIgnoreCase("setghoulspawn")) {
                        setLocation("GhoulSpawn", p.getLocation());
                        saveConfigs();

                        p.sendMessage(prefix + "You've set the ghoulspawn's location");

                    } else if (args[0].equalsIgnoreCase("setplayerspawn")) {
                        setLocation("PlayerSpawn", p.getLocation());
                        saveConfigs();

                        p.sendMessage(prefix + "You've set the playerspawn's location");

                    }
                } else if (p.hasPermission("gh.quit.use")) {

                    if (args[0].equalsIgnoreCase("quit")) {
                        if (pList.contains(p.getUniqueId())) {
                            pList.remove(p.getUniqueId());

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

                } else {
                    p.sendMessage(prefix + RED + "You don't have the right permission !");
                }
            }
        }
        return true;
    }

    private static void setLocation(String path, Location loc) {
        locs.set(path + ".WorldName", loc.getWorld().getName());

        locs.set(path + ".X", loc.getX());

        locs.set(path + ".Y", loc.getY());

        locs.set(path + ".Z", loc.getZ());

        locs.set(path + ".Yaw", loc.getYaw());

        locs.set(path + ".Pitch", loc.getPitch());

    }
}
