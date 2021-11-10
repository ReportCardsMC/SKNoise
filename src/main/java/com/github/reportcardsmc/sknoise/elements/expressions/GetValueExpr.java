package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class GetValueExpr extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(GetValueExpr.class, Number.class, ExpressionType.COMBINED, "[sknoise] value of %noisegenerator% at %location%");
    }

    Expression<NoiseGenerator> generatorExpression;
    Expression<Location> locationExpression;

    @Override
    protected @Nullable
    Number[] get(Event e) {
        NoiseGenerator generator = generatorExpression.getSingle(e);
        Location location = locationExpression.getSingle(e);
        if (generator == null || location == null) return new Number[]{-1};
        return new Number[]{(generator).GetNoise((float) location.getX(), (float) location.getY(), (float) location.getZ())};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Get value of gen: " + generatorExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        generatorExpression = (Expression<NoiseGenerator>) exprs[0];
        locationExpression = (Expression<Location>) exprs[1];
        return true;
    }
}
