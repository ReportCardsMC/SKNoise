package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Simplex extends NoiseGenerator {
    public Simplex(long seed) {
        SetNoiseType(NoiseType.OpenSimplex2);
        init();
        Seed(seed);
        this.noise = ValidGenerators.SIMPLEX;
    }
    public Simplex() {
        SetNoiseType(NoiseType.OpenSimplex2);
        init();
        Seed(0);
        this.noise = ValidGenerators.SIMPLEX;
    }
}
