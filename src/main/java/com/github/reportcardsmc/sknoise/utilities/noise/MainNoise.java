package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.FastNoise;

public abstract class MainNoise extends FastNoise {
    int seed = 0;
    private long bitMax = 2147483647;

    public void Seed(long seed) {
        SetSeed(Math.toIntExact(Math.floorMod(seed, bitMax * 2) - bitMax));
    }
}
