package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.FastNoise;
import com.github.reportcardsmc.sknoise.utilities.enums.FractalType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public abstract class NoiseGenerator extends FastNoise {
    int seed = 0;
    private long bitMax = 2147483647;
    public ValidGenerators noise = ValidGenerators.VALUE;

    abstract public String typeString();

    public void Seed(long seed) {
        SetSeed(Math.toIntExact(Math.floorMod(seed, bitMax * 2) - bitMax));
    }

    public void init() {
        SetFractalOctaves(1);
        SetFractalType(FractalType.FBm);
    }
}
