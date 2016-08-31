package fr.zomdev.gh;

import fr.zomdev.gh.events.PlayerJoin;
import fr.zomdev.gh.events.PlayerLogin;
import fr.zomdev.gh.events.PlayerQuit;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

import static fr.zomdev.gh.utils.ConfigUtil.setupConfigs;

/**
 * Created by Thomas on 30/08/2016.
 */
public class Main extends JavaPlugin {

    // On instancie l'instance du plugin
    private static Main instance;

    // Getter de l'instance
    public static Main getInstance() {
        return instance;
    }

    // Le prefix pour tous les messages aux joueurs
    public static final String prefix = ChatColor.AQUA + "[ GhoulHunt ] ";

    // La liste des joueurs qui ont rejoints le jeu
    public static ArrayList<UUID> pList = new ArrayList<>();

    @Override
    public void onEnable() {
        // On définit l'instance comme this
        instance = this;

        // Mise en place des configs tiers
        setupConfigs();

        // On enregistre la config de spigot
        getConfig().options().copyDefaults(true);
        saveConfig();

        // On définit le status de jeu sur LOBBY
        GameState.setState(GameState.LOBBY);

        // On Enregistre les Evènements
        registerEvents();
    }

    @Override
    public void onDisable() {
        // On supprime le contenu de la liste des joueurs
        pList.clear();
    }

    private void registerEvents() {

        // On instancie le PluginManager
        PluginManager pm = Bukkit.getPluginManager();

        // Quand le joueur rejoint le jeu
        pm.registerEvents(new PlayerJoin(), this);

        // Quand le joueur quitte le jeu
        pm.registerEvents(new PlayerQuit(), this);

        // Quand le joueur se connecte au serveur
        pm.registerEvents(new PlayerLogin(), this);
    }


}
