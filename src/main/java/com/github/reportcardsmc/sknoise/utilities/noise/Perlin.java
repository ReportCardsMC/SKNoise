package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Perlin extends NoiseGenerator {
    Perlin(long seed) {
        this.SetNoiseType(NoiseType.Perlin);
        Seed(seed);
        SetFractalOctaves(1);
        this.noise = ValidGenerators.PERLIN;
    }
    Perlin() {
        this.SetNoiseType(NoiseType.Perlin);
        Seed(0);
        SetFractalOctaves(1);
        this.noise = ValidGenerators.PERLIN;
    }
}
