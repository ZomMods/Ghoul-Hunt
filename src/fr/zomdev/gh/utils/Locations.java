package fr.zomdev.gh.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by ZomDev on 31/08/2016.
 */
public class Locations {

    private static FileConfiguration locs = ConfigUtil.getLocConfig();

    private static Location LOBBY = new Location(Bukkit.getWorld(locs.getString("Lobby.WorldName")), locs.getDouble("Lobby.X"), locs.getDouble("Lobby.Y"), locs.getDouble("Lobby.Z"), locs.getLong("Lobby.Yaw"), locs.getLong("Lobby.Pitch"));
    private static Location GHOUL_SPAWN = new Location(Bukkit.getWorld(locs.getString("GhoulSpawn.WorldName")), locs.getDouble("GhoulSpawn.X"), locs.getDouble("GhoulSpawn.Y"), locs.getDouble("GhoulSpawn.Z"), locs.getLong("GhoulSpawn.Yaw"), locs.getLong("GhoulSpawn.Pitch"));
    private static Location PLAYER_SPAWN = new Location(Bukkit.getWorld(locs.getString("PlayerSpawn.WorldName")), locs.getDouble("PlayerSpawn.X"), locs.getDouble("PlayerSpawn.Y"), locs.getDouble("PlayerSpawn.Z"), locs.getLong("PlayerSpawn.Yaw"), locs.getLong("PlayerSpawn.Pitch"));


    public static Location getLOBBY() {
        return LOBBY;
    }

    public static Location getGhoulSpawn() {
        return GHOUL_SPAWN;
    }

    public static Location getPlayerSpawn() {
        return PLAYER_SPAWN;
    }
}
