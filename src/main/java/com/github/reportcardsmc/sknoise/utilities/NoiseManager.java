package com.github.reportcardsmc.sknoise.utilities;


import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

import java.io.Serializable;

public class NoiseManager implements Serializable {

    private int simplex_seed;
    private int perlin_seed;
    public PerlinNoiseGenerator perlin_generator;
    public SimplexNoiseGenerator simplex_generator;

    public NoiseManager(int seed) {
        perlin_seed = seed;
        simplex_seed = seed;
        perlin_generator = new PerlinNoiseGenerator(seed);
        simplex_generator = new SimplexNoiseGenerator(seed);
    }

    public PerlinNoiseGenerator getPerlin() {
        return perlin_generator;
    }
    public SimplexNoiseGenerator getSimplex() {
        return simplex_generator;
    }

    public int getSeed(NoiseType type) {
        if (type == NoiseType.PERLIN) return perlin_seed;
        if (type == NoiseType.SIMPLEX) return simplex_seed;
        return 0;
    }

    public boolean setSeed(NoiseType type, int seed) {
        try {
            if (type == NoiseType.PERLIN) {
                perlin_seed = seed;
                perlin_generator = new PerlinNoiseGenerator(perlin_seed);
            }
            if (type == NoiseType.SIMPLEX) {
                simplex_seed = seed;
                simplex_generator = new SimplexNoiseGenerator(simplex_seed);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
