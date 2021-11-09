package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.enums.CellularReturnType;
import com.github.reportcardsmc.sknoise.utilities.enums.FractalType;

public class CellularReturnTypes {

    static {
        Classes.registerClass(new ClassInfo<>(CellularReturnType.class, "cellularreturntype")
                .user("cellular[ ]return[ ]types?")
                .name("cellular return type")
                .description("Type of a cellular return. Valid: CellValue, Distance, Distance2, Distance2Add, Distance2Sub, Distance2Mul, Distance2Div")
                .parser(new Parser<CellularReturnType>() {

                    private final String[] types = new String[CellularReturnType.values().length];
                    {
                        int i = 0;
                        for (final CellularReturnType gen : CellularReturnType.values()) {
                            types[i++] = gen.name();
                        }
                    }

                    @Override
                    public CellularReturnType parse(String s, ParseContext context) {
                        for (int i = 0; i < types.length; i++) {
                            if (s.equalsIgnoreCase(types[i])) return CellularReturnType.values()[i];
                        }
                        return null;
                    }

                    @Override
                    public String toString(CellularReturnType o, int flags) {
                        return "Cellular Return Type: " + o.toString();
                    }

                    @Override
                    public String toVariableNameString(CellularReturnType o) {
                        return "cellularreturn-" + o.toString();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "cellularreturn-.+";
                    }
                })
                .serializer(new EnumSerializer<>(CellularReturnType.class)));
    }

}
