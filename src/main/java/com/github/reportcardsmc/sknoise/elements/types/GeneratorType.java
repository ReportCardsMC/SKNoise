package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;

public class GeneratorType {

    static {
        Classes.registerClass(new ClassInfo<>(ValidGenerators.class, "generator type")
                .user("generator[ ]types?")
                .name("generator type")
                .description("Type of a generator")
                .parser(new Parser<ValidGenerators>() {
                    @Override
                    public String toString(ValidGenerators o, int flags) {
                        return "Gen Type: " + o.toString();
                    }

                    @Override
                    public String toVariableNameString(ValidGenerators o) {
                        return "gentype-" + o.toString();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "gentype-.+";
                    }
                })
                .serializer(new EnumSerializer<>(ValidGenerators.class)));
    }

}
