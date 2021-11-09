package com.github.reportcardsmc.sknoise.elements.expressions.cellular;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.utilities.enums.CellularReturnType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CellularReturnExpr extends SimpleExpression<CellularReturnType> {

    static {
        Skript.registerExpression(CellularReturnExpr.class, CellularReturnType.class, ExpressionType.COMBINED, "[sknoise] cellular return type of %noisegenerator%");
    }

    Expression<NoiseGenerator> noiseGeneratorExpression;

    @Override
    protected @Nullable
    CellularReturnType[] get(Event e) {
        NoiseGenerator generator = noiseGeneratorExpression.getSingle(e);
        if (generator == null || !generator.noise.equals(ValidGenerators.CELLULAR)) return null;
        return new CellularReturnType[]{generator.mCellularReturnType};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends CellularReturnType> getReturnType() {
        return CellularReturnType.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Cellular Return type of generator: " + noiseGeneratorExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        noiseGeneratorExpression = (Expression<NoiseGenerator>) exprs[0];
        return true;
    }
}
