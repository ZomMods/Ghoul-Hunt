package fr.zomdev.gh.cmds;

import fr.zomdev.gh.utils.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static fr.zomdev.gh.Main.prefix;
import static fr.zomdev.gh.utils.ConfigUtil.saveConfigs;
import static org.bukkit.ChatColor.RED;

/**
 * Created by ZomDev on 01/09/2016.
 */
public class ghCommand implements CommandExecutor {

    private static FileConfiguration locs = ConfigUtil.getLocConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cYou must be a player to send this command !");

        }else{
            Player p = (Player)sender;

            if(args.length == 0){
                p.sendMessage(prefix + RED + "You must use an argument!");
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("setlobby")){
                    setLocation("Lobby", p.getLocation());
                    saveConfigs();
                }else if(args[0].equalsIgnoreCase("setghoulspawn")){
                    setLocation("GhoulSpawn", p.getLocation());
                    saveConfigs();

                }else if(args[0].equalsIgnoreCase("setplayerspawn")){
                    setLocation("PlayerSpawn", p.getLocation());
                    saveConfigs();

                }
            }

        }
        return true;
    }

    private static void setLocation(String path, Location loc){
        locs.set(path + ".X", loc.getX());
        locs.set(path + ".Y", loc.getY());
        locs.set(path + ".Z", loc.getZ());
        locs.set(path + ".Yaw", loc.getYaw());
        locs.set(path + ".Pitch", loc.getPitch());
    }
}
