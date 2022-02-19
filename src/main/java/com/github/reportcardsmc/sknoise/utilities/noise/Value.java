package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Value extends NoiseGenerator {
    public Value(long seed) {
        SetNoiseType(NoiseType.Value);
        init();
        Seed(seed);
        this.noise = ValidGenerators.VALUE;
    }
    public Value() {
        SetNoiseType(NoiseType.Value);
        init();
        Seed(0);
        this.noise = ValidGenerators.VALUE;
    }

    @Override
    public String typeString() {
        return "Value";
    }
}
