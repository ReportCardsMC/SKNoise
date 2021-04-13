package com.github.reportcardsmc.sknoise.utilities;

public class Cellular extends FastNoise {
    int seed = 0;
    private long bitMax = 2147483647;
    Cellular(long seed) {
        setNoiseType(6);
        this.seed = Math.toIntExact(Math.floorMod(seed, bitMax * 2) - bitMax);
        setSeed(this.seed);
        setFractalOctaves(1);
    }

    public void setWrappedSeed(long seed) {
        this.seed = Math.toIntExact(Math.floorMod(seed, bitMax * 2) - bitMax);
        setSeed(this.seed);
    }
}
