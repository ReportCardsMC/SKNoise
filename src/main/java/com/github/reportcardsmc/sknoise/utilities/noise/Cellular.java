package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Cellular extends NoiseGenerator {
    public Cellular(long seed) {
        SetNoiseType(NoiseType.Cellular);
        init();
        Seed(seed);
        this.noise = ValidGenerators.CELLULAR;
    }
    public Cellular() {
        SetNoiseType(NoiseType.Cellular);
        init();
        Seed(0);
        this.noise = ValidGenerators.CELLULAR;
    }
}
