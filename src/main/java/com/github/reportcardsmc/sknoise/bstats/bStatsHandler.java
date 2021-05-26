package com.github.reportcardsmc.sknoise.bstats;

import com.github.reportcardsmc.sknoise.SkNoise;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class bStatsHandler {
    private static Metrics metrics;


    public bStatsHandler() {
        int pluginID = 10639;
        metrics = new Metrics(SkNoise.instance, pluginID);
        metrics.addCustomChart(new Metrics.DrilldownPie("skript_version", () -> {
            Map<String,Map<String, Integer>> map = new HashMap<>();
            String SkriptVersion = Bukkit.getPluginManager().getPlugin("Skript").getDescription().getVersion();
            Map<String, Integer> entry = new HashMap<>();
            entry.put(SkriptVersion, 1);
            map.put("Skript " + SkriptVersion, entry);
            return map;
        }));
    }
}
