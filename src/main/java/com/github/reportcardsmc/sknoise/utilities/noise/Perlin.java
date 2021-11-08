package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;

public class Perlin extends MainNoise {
    Perlin(long seed) {
        this.SetNoiseType(NoiseType.Perlin);
        Seed(seed);
        SetFractalOctaves(1);
    }
    Perlin() {
        this.SetNoiseType(NoiseType.Perlin);
        Seed(0);
        SetFractalOctaves(1);
    }
}
