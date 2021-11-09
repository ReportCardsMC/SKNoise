package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Simplex extends NoiseGenerator {
    Simplex(long seed) {
        SetNoiseType(NoiseType.OpenSimplex2);
        Seed(seed);
        SetFractalOctaves(1);
        this.noise = ValidGenerators.SIMPLEX;
    }
    Simplex() {
        SetNoiseType(NoiseType.OpenSimplex2);
        Seed(0);
        SetFractalOctaves(1);
        this.noise = ValidGenerators.SIMPLEX;
    }
}
