package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

import java.util.Objects;

public class GeneratorType {

    static {
        Classes.registerClass(new ClassInfo<>(ValidGenerators.class, "generatortype")
                .user("generator[ ]types?")
                .name("generator type")
                .description("Type of a generator. Valid: Perlin, Cellular/Voronoi, Simplex, Value")
                .parser(new Parser<ValidGenerators>() {

                    private final String[] types = new String[ValidGenerators.values().length];
                    {
                        int i = 0;
                        for (final ValidGenerators gen : ValidGenerators.values()) {
                            types[i++] = gen.name();
                        }
                    }

                    @Override
                    public ValidGenerators parse(String s, ParseContext context) {
                        for (int i = 0; i < types.length; i++) {
                            if (s.equalsIgnoreCase(types[i])) {
                                if (Objects.equals(types[i], "VORONOI")) return ValidGenerators.CELLULAR;
                                return ValidGenerators.values()[i];
                            }
                        }
                        return null;
                    }

                    @Override
                    public String toString(ValidGenerators o, int flags) {
                        return "Gen Type: " + o.toString();
                    }

                    @Override
                    public String toVariableNameString(ValidGenerators o) {
                        return "gentype-" + o.toString();
                    }
                })
                .serializer(new EnumSerializer<>(ValidGenerators.class)));
    }

}
