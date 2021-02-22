package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.SkNoise;
import org.bukkit.event.Event;
import com.github.reportcardsmc.sknoise.utilities.NoiseManager;

import javax.annotation.Nullable;

public class ExprPerlinNoise extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprPerlinNoise.class, Double.class, ExpressionType.COMBINED, "[sknoise] perlin noise [at] [x ]%number%[,] [[y ]%number%[,] [[z ]%number%]]");
    }

    private Expression<Number> xLoc;
    private Expression<Number> yLoc;
    private Expression<Number> zLoc;


    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "PERLIN WOAH";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.xLoc = (Expression<Number>) expressions[0];
        this.yLoc = (Expression<Number>) expressions[1];
        this.zLoc = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        NoiseManager noiseManager = SkNoise.instance.getNoiseManager();
        Number x = xLoc.getSingle(event);
        Number y = null;
        Number z = null;
        if (yLoc != null) {
            y = yLoc.getSingle(event);
            if (zLoc != null) {
                z = zLoc.getSingle(event);
            }
        }
        Double noise = null;
        if (y == null) noise = noiseManager.getPerlin().noise(x.doubleValue());
        if (z == null) noise = noiseManager.getPerlin().noise(x.doubleValue(), y.doubleValue());
        if (z != null) noise = noiseManager.getPerlin().noise(x.doubleValue(), y.doubleValue(), z.doubleValue());
        return new Double[]{noise};
    }


}
