package com.github.reportcardsmc.sknoise;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.github.reportcardsmc.sknoise.bstats.bStatsHandler;
import com.github.reportcardsmc.sknoise.utilities.NoiseManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;

public final class SkNoise extends JavaPlugin {
    private final HashMap<String, Integer> logList = new HashMap<>();
    private final HashMap<String, Integer> maxList = new HashMap<>();
    public static SkNoise instance;
    private static NoiseManager noiseManager;
    private static SkriptAddon addon;
    private static boolean loaded = false;
    public static bStatsHandler bStats;
    @Override
    public void onEnable() {
        // Plugin startup logic
        maxList.put("start", 5);

        // Skript Registration
        log("&eAttempting to register addon", "start");
        if (addon == null) addon = Skript.registerAddon(this);

        log("&aRegistered addon successfully", "start");
        log("&eAttempting to load addon classes", "start");
        if (loaded) {
            try {
                addon.loadClasses("com.github.reportcardsmc.sknoise", "elements");
            } catch (IOException e) {
                Bukkit.getLogger().warning("Error loading skript classes");
                e.printStackTrace();
            }
        }
        log("&aLoaded skript classes", "start");


        log("&fPlugin loaded", "start");

        //Variable Setting
        instance = this;
        noiseManager = new NoiseManager(0);
        loaded = true;

        bStats = new bStatsHandler();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
    public NoiseManager getNoiseManager() {
        return noiseManager;
    }
    private void log(String text, String id) {
        if (!logList.containsKey(id)) logList.put(id, 0);
        int iteration = logList.get(id) + 1;
        logList.put(id, iteration);
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&e[SkNoise] &f" + text + " &b[" + iteration + "/" + maxList.get(id) + "]"));
    }

}
