package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseTypes;

public class Perlin extends MainNoise {
    Perlin(long seed) {
        this.SetNoiseType(NoiseTypes.NoiseType.Perlin);
        Seed(seed);
        SetFractalOctaves(1);
    }
    Perlin() {
        this.SetNoiseType(NoiseTypes.NoiseType.Perlin);
        Seed(0);
        SetFractalOctaves(1);
    }
}
