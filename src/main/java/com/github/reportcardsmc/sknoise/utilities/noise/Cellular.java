package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseTypes;

public class Cellular extends MainNoise {
    Cellular(long seed) {
        SetNoiseType(NoiseTypes.NoiseType.Cellular);
        Seed(seed);
        SetFractalOctaves(1);
    }
    Cellular() {
        SetNoiseType(NoiseTypes.NoiseType.Cellular);
        Seed(0);
        SetFractalOctaves(1);
    }
}
