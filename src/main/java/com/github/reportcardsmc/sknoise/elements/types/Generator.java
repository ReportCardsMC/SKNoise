package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.jetbrains.annotations.NotNull;

public class Generator {

    static {
        Classes.registerClass(new ClassInfo<>(NoiseGenerator.class, "noisegenerator")
                .user("noise[ ]generators?")
                .name("noise generator")
                .description("Represents a noise generator")
                .parser(new Parser<NoiseGenerator>() {
                    @NotNull
                    @Override
                    public String toString(NoiseGenerator o, int flags) {
                        return o.typeString() + " Noise Generator with seed " + o.mSeed + String.format("(%s oct/%s freq/%s fract/%s gain/%s lacu/%s weight str)",
                                o.mOctaves, o.mFrequency, o.mFractalType.name(), o.mGain, o.mLacunarity, o.mWeightedStrength);
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toVariableNameString(NoiseGenerator o) {
                        return null;
                    }
                }));
    }

}
