package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.enums.FractalType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class FractalTypes {

    static {
        Classes.registerClass(new ClassInfo<>(FractalType.class, "fractaltype")
                .user("fractal[ ]types?")
                .name("fractal type")
                .description("Type of a fractal")
                .parser(new Parser<FractalType>() {

                    private final String[] types = new String[FractalType.values().length];
                    {
                        int i = 0;
                        for (final FractalType gen : FractalType.values()) {
                            types[i++] = gen.name();
                        }
                    }

                    @Override
                    public FractalType parse(String s, ParseContext context) {
                        for (int i = 0; i < types.length; i++) {
                            if (s.equalsIgnoreCase(types[i])) return FractalType.values()[i];
                        }
                        return null;
                    }

                    @Override
                    public String toString(FractalType o, int flags) {
                        return "Fractal Type: " + o.toString();
                    }

                    @Override
                    public String toVariableNameString(FractalType o) {
                        return "fractaltype-" + o.toString();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "fractaltype-.+";
                    }
                })
                .serializer(new EnumSerializer<>(FractalType.class)));
    }

}
