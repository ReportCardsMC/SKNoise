package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;

public class Simplex extends MainNoise {
    Simplex(long seed) {
        SetNoiseType(NoiseType.OpenSimplex2);
        Seed(seed);
        SetFractalOctaves(1);
    }
    Simplex() {
        SetNoiseType(NoiseType.OpenSimplex2);
        Seed(0);
        SetFractalOctaves(1);
    }
}
