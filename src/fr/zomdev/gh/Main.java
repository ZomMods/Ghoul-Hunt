package fr.zomdev.gh;

import fr.zomdev.gh.events.PlayerJoin;
import fr.zomdev.gh.events.PlayerQuit;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

import static fr.zomdev.gh.utils.ConfigUtil.setupConfigs;

/**
 * Created by Thomas on 30/08/2016.
 */
public class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance(){
        return instance;
    }

    public static final String prefix = ChatColor.AQUA + "[ GhoulHunt ] ";

    public static ArrayList<UUID> pList = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        setupConfigs();

        getConfig().options().copyDefaults(true);
        saveConfig();

        GameState.setState(GameState.LOBBY);
    }

    @Override
    public void onDisable() {
        pList.clear();
    }

    private void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
    }


}
