package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;

public class Generator {

    static {
        Classes.registerClass(new ClassInfo<>(NoiseGenerator.class, "noise generator")
                .user("noise[ ]generators?")
                .name("noise generator")
                .description("Represents a noise generator")
                .parser(new Parser<NoiseGenerator>() {
                    @Override
                    public String toString(NoiseGenerator o, int flags) {
                        return "Generator: " + o.noise;
                    }

                    @Override
                    public String toVariableNameString(NoiseGenerator o) {
                        return "generator-" + o.noise;
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "generator-.+";
                    }
                }));
    }

}
