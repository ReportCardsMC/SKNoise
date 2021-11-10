package com.github.reportcardsmc.sknoise.elements.expressions.noise.cellular;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.github.reportcardsmc.sknoise.utilities.enums.CellularReturnType;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CellularReturnExpr extends SimpleExpression<CellularReturnType> {

    static {
        Skript.registerExpression(CellularReturnExpr.class, CellularReturnType.class, ExpressionType.COMBINED, "[sknoise] (cellular|voronoi) return type of %noisegenerator%");
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

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) ? CollectionUtils.array(CellularReturnType.class) : null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        NoiseGenerator generator = noiseGeneratorExpression.getSingle(e);
        CellularReturnType type = (CellularReturnType) delta[0];
        if (generator == null || type == null || generator.noise != ValidGenerators.CELLULAR) return;
        if (mode == Changer.ChangeMode.SET) {
            generator.SetCellularReturnType(type);
        } else if (mode == Changer.ChangeMode.RESET) {
            generator.SetCellularReturnType(CellularReturnType.Distance);
        }
    }
}
