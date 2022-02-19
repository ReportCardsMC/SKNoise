package com.github.reportcardsmc.sknoise.utilities.noise;

import com.github.reportcardsmc.sknoise.utilities.enums.NoiseType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class Perlin extends NoiseGenerator {
    public Perlin(long seed) {
        SetNoiseType(NoiseType.Perlin);
        init();
        Seed(seed);
        this.noise = ValidGenerators.PERLIN;
    }
    public Perlin() {
        SetNoiseType(NoiseType.Perlin);
        init();
        Seed(0);
        this.noise = ValidGenerators.PERLIN;
    }

    @Override
    public String typeString() {
        return "Perlin";
    }
}
