package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Cellular extends NoiseGenerator {
    Cellular(long seed) {
        SetNoiseType(NoiseType.Cellular);
        Seed(seed);
        SetFractalOctaves(1);
        this.noise = ValidGenerators.CELLULAR;
    }
    Cellular() {
        SetNoiseType(NoiseType.Cellular);
        Seed(0);
        SetFractalOctaves(1);
        this.noise = ValidGenerators.CELLULAR;
    }
}
