package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;

public class Generator {

    static {
        Classes.registerClass(new ClassInfo<>(NoiseGenerator.class, "noisegenerator")
                .user("noise[ ]generators?")
                .name("noise generator")
                .description("Represents a noise generator"));
    }

}
