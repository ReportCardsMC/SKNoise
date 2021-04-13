package com.github.reportcardsmc.sknoise.utilities;


import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

import java.io.Serializable;

public class NoiseManager implements Serializable {

    private long simplex_seed;
    private long perlin_seed;
    private long cellular_seed;
    public PerlinNoiseGenerator perlin_generator;
    public SimplexNoiseGenerator simplex_generator;
    public Cellular cellular_generator;


    public NoiseManager(long seed) {
        perlin_seed = seed;
        simplex_seed = seed;
        cellular_seed = seed;
        perlin_generator = new PerlinNoiseGenerator(seed);
        simplex_generator = new SimplexNoiseGenerator(seed);
        cellular_generator = new Cellular(seed);
    }

    public PerlinNoiseGenerator getPerlin() {
        return perlin_generator;
    }
    public SimplexNoiseGenerator getSimplex() {
        return simplex_generator;
    }
    public Cellular getCellular() {return cellular_generator; }

    public long getSeed(NoiseType type) {
        if (type == NoiseType.PERLIN) return perlin_seed;
        if (type == NoiseType.SIMPLEX) return simplex_seed;
        if (type == NoiseType.CELLULAR) return cellular_generator.getSeed();
        return 0;
    }

    public boolean setSeed(NoiseType type, long seed) {
        try {
            if (type == NoiseType.PERLIN) {
                perlin_seed = seed;
                perlin_generator = new PerlinNoiseGenerator(perlin_seed);
            } else if (type == NoiseType.SIMPLEX) {
                simplex_seed = seed;
                simplex_generator = new SimplexNoiseGenerator(simplex_seed);
            } else if (type == NoiseType.CELLULAR) {
                cellular_seed = seed;
                cellular_generator.setWrappedSeed(seed);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
