package com.github.reportcardsmc.sknoise.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.github.reportcardsmc.sknoise.utilities.enums.CellularDistanceFunction;

public class CellularDistanceTypes {

    static {
        Classes.registerClass(new ClassInfo<>(CellularDistanceFunction.class, "cellulardistancetype")
                .user("cellular[ ]distance[ ]types?")
                .name("cellular distance type")
                .description("Type of a cellular distance. Valid: Euclidean, EuclideanSq, Manhattan, Hybrid")
                .parser(new Parser<CellularDistanceFunction>() {

                    private final String[] types = new String[CellularDistanceFunction.values().length];
                    {
                        int i = 0;
                        for (final CellularDistanceFunction gen : CellularDistanceFunction.values()) {
                            types[i++] = gen.name();
                        }
                    }

                    @Override
                    public CellularDistanceFunction parse(String s, ParseContext context) {
                        for (int i = 0; i < types.length; i++) {
                            if (s.equalsIgnoreCase(types[i])) return CellularDistanceFunction.values()[i];
                        }
                        return null;
                    }

                    @Override
                    public String toString(CellularDistanceFunction o, int flags) {
                        return "Cellular Distance Type: " + o.toString();
                    }

                    @Override
                    public String toVariableNameString(CellularDistanceFunction o) {
                        return "cellulardistance-" + o.toString();
                    }

                })
                .serializer(new EnumSerializer<>(CellularDistanceFunction.class)));
    }

}
