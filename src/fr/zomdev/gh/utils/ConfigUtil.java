package fr.zomdev.gh.utils;

import fr.zomdev.gh.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZomDev on 31/08/2016.
 */
public class ConfigUtil {

    private static String path = Main.getInstance().getDataFolder().getPath();

    private static File locations = new File(path, "locations.yml");
    private static FileConfiguration locConfig = YamlConfiguration.loadConfiguration(locations);

    public static void setupConfigs(){

        setDefaults();

        copyDefaults();

        saveConfigs();

    }

    private static void saveConfig(FileConfiguration config, File configFile) {

        try {

            config.save(configFile);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void saveConfigs(){

        saveConfig(locConfig, locations);

    }

    private static void setDefaults(){

        locConfig.setDefaults(YamlConfiguration.loadConfiguration(Main.getInstance().getResource("locations.yml")));

    }

    private static void copyDefaults(){

        locConfig.options().copyDefaults(true);

    }

    public static FileConfiguration getLocConfig() {
        return locConfig;
    }
}
