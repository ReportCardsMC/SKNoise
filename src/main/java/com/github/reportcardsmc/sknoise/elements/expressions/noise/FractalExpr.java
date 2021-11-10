package com.github.reportcardsmc.sknoise.elements.expressions.noise;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.github.reportcardsmc.sknoise.utilities.enums.FractalType;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class FractalExpr extends SimpleExpression<FractalType> {

    static {
        Skript.registerExpression(FractalExpr.class, FractalType.class, ExpressionType.COMBINED, "[sknoise] fractal [type] of %noisegenerator%");
    }

    Expression<NoiseGenerator> generatorExpression;
    @Override
    protected @Nullable
    FractalType[] get(Event e) {
        NoiseGenerator generator = generatorExpression.getSingle(e);
        if (generator == null) return null;
        return new FractalType[]{generator.mFractalType};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends FractalType> getReturnType() {
        return FractalType.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "fractal type of: " + generatorExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        generatorExpression = (Expression<NoiseGenerator>) exprs[0];
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) ? CollectionUtils.array(FractalType.class) : null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        NoiseGenerator generator = generatorExpression.getSingle(e);
        FractalType type = (FractalType) delta[0];
        if (generator == null || type == null) return;
        if (mode == Changer.ChangeMode.SET) {
            generator.SetFractalType(type);
        } else if (mode == Changer.ChangeMode.RESET) {
            generator.SetFractalType(FractalType.FBm);
        }
    }
}
