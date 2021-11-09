package com.github.reportcardsmc.sknoise.elements.expressions.cellular;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.github.reportcardsmc.sknoise.utilities.enums.CellularDistanceFunction;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CellularDistanceExpr extends SimpleExpression<CellularDistanceFunction> {

    static {
        Skript.registerExpression(CellularDistanceExpr.class, CellularDistanceFunction.class, ExpressionType.COMBINED, "[sknoise] cellular distance [function] of %noisegenerator%");
    }

    Expression<NoiseGenerator> noiseGeneratorExpression;

    @Override
    protected @Nullable
    CellularDistanceFunction[] get(Event e) {
        NoiseGenerator generator = noiseGeneratorExpression.getSingle(e);
        if (generator == null || !generator.noise.equals(ValidGenerators.CELLULAR)) return null;
        return new CellularDistanceFunction[]{generator.mCellularDistanceFunction};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends CellularDistanceFunction> getReturnType() {
        return CellularDistanceFunction.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Cellular Distance Function of generator: " + noiseGeneratorExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        noiseGeneratorExpression = (Expression<NoiseGenerator>) exprs[0];
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) ? CollectionUtils.array(CellularDistanceFunction.class) : null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        NoiseGenerator generator = noiseGeneratorExpression.getSingle(e);
        CellularDistanceFunction type = (CellularDistanceFunction) delta[0];
        if (generator == null || type == null || generator.noise != ValidGenerators.CELLULAR) return;
        if (mode == Changer.ChangeMode.SET) {
            generator.SetCellularDistanceFunction(type);
        } else if (mode == Changer.ChangeMode.RESET) {
            generator.SetCellularDistanceFunction(CellularDistanceFunction.EuclideanSq);
        }
    }
}
