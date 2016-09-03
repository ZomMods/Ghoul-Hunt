package fr.zomdev.gh;

import fr.zomdev.gh.cmds.ghCommand;
import fr.zomdev.gh.events.*;
import fr.zomdev.gh.kits.GhoulKit;
import fr.zomdev.gh.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static fr.zomdev.gh.utils.ConfigUtil.setupConfigs;

/**
 * Created by ZomDev on 30/08/2016.
 */
public class Main extends JavaPlugin {

    //TODO Systeme de quinques
    //TODO Systeme de victoire pour les joueurs
    //TODO Les Details(Sons,Particules...)


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

    // La liste des ghoules
    public static ArrayList<UUID> ghouls = new ArrayList<>();

    // La liste des joueurs entrain de se faire dévorer
    public static ArrayList<UUID> devoured = new ArrayList<>();

    public static ArrayList<UUID> frozen = new ArrayList<>();

    public static ArrayList<UUID> deads = new ArrayList<>();

    private ArrayList<String> ghAliases = new ArrayList<>();

    private ArrayList<String> args = new ArrayList<>();

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

        GhoulKit.registerItems();

        // On Enregistre les Evènements
        registerEvents();

        // On enregistre les alias et le tabcompleter pour la commande
        registerAliases();
        registerTabCompleter();

        // On Enregistre les Commandes
        getCommand("ghoulhunt").setExecutor(new ghCommand());

        getCommand("ghoulhunt").setAliases(ghAliases);

        getCommand("ghoulhunt").setPermission("ghoulhunt.setLocations");

        getCommand("ghoulhunt").setTabCompleter(new TabCompleter() {

            @Override
            public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
                return args;
            }

        });
    }

    @Override
    public void onDisable() {
        // On supprime le contenu de la liste des joueurs
        pList.clear();
        ghouls.clear();
        devoured.clear();
        deads.clear();
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

        // L'évènement du système de joueur dévoré
        pm.registerEvents(new GhoulDevourPlayer(), this);

        // Qaund le joueur casse un block
        pm.registerEvents(new PlayerBreak(), this);

        // Quand le joueur bouge
        pm.registerEvents(new PlayerMove(), this);
    }

    private void registerAliases(){
        ghAliases.add("gh");
    }

    private void registerTabCompleter(){
        args.add("setlobby");
        args.add("setghoulspawn");
        args.add("setplayerspawn");
    }

}
