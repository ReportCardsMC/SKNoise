package com.github.reportcardsmc.sknoise.bstats;

import com.github.reportcardsmc.sknoise.SkNoise;
import org.bstats.bukkit.Metrics;

public class bStatsHandler {
    private static Metrics metrics;
    private int pluginID = 10639;

    public bStatsHandler() {
        metrics = new Metrics(SkNoise.instance, pluginID);
    }
}
