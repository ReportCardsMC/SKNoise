package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.SkNoise;
import com.github.reportcardsmc.sknoise.utilities.NoiseManager;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprSimplexNoise extends SimpleExpression<Double> {

    static {
        String[] patterns = {"[sknoise] simplex noise [at] [x ]%number%[,] [[y ]%number%[,] [[z ]%number%]]",
                "[sknoise] simplex noise [at] %location%"};
        Skript.registerExpression(ExprSimplexNoise.class, Double.class, ExpressionType.COMBINED, patterns);
    }

    private Expression<Number> xLoc;
    private Expression<Number> yLoc;
    private Expression<Number> zLoc;
    private Expression<Location> location;


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
        return "Simplex Expression";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (i == 0) {
            this.xLoc = (Expression<Number>) expressions[0];
            this.yLoc = (Expression<Number>) expressions[1];
            this.zLoc = (Expression<Number>) expressions[2];
        } else if (i == 1) {
            this.location = (Expression<Location>) expressions[0];
        }
        return true;
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        NoiseManager noiseManager = SkNoise.instance.getNoiseManager();
        Number x = null;
        Number y = null;
        Number z = null;
        if (this.xLoc == null) {
            if (this.location != null) {
                Location loc = this.location.getSingle(event);
                x = loc.getX();
                y = loc.getY();
                z = loc.getZ();
            }

        } else x = xLoc.getSingle(event);
        if (yLoc != null) {
            y = yLoc.getSingle(event);
            if (zLoc != null) {
                z = zLoc.getSingle(event);
            }
        }
        Double noise = null;
        if (y == null) noise = noiseManager.getSimplex().noise(x.doubleValue());
        if (z == null) noise = noiseManager.getSimplex().noise(x.doubleValue(), y.doubleValue());
        if (z != null) noise = noiseManager.getSimplex().noise(x.doubleValue(), y.doubleValue(), z.doubleValue());
        return new Double[]{noise};
    }


}
