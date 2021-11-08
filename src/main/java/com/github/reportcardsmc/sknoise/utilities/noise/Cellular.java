package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;

public class Cellular extends MainNoise {
    Cellular(long seed) {
        SetNoiseType(NoiseType.Cellular);
        Seed(seed);
        SetFractalOctaves(1);
    }
    Cellular() {
        SetNoiseType(NoiseType.Cellular);
        Seed(0);
        SetFractalOctaves(1);
    }
}
