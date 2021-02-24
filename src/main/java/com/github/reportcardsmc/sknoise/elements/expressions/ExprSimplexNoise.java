package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.SkNoise;
import com.github.reportcardsmc.sknoise.utilities.NoiseManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprSimplexNoise extends SimpleExpression<Double> {

    static {
        String[] patterns = {"[sknoise] simplex noise [at] [x ]%number%[,] [[y ]%number%[,] [[z ]%number%]]",
                "[sknoise] simplex noise at loc[ation] %location%"};
        Skript.registerExpression(ExprSimplexNoise.class, Double.class, ExpressionType.COMBINED, patterns);
    }

    private Expression<Number> xLoc;
    private Expression<Number> yLoc;
    private Expression<Number> zLoc;
    private Expression<Location> location;
    private int expressionUsed;


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
        expressionUsed = i;
        if (expressionUsed == 0) {
            this.xLoc = (Expression<Number>) expressions[0];
            this.yLoc = (Expression<Number>) expressions[1];
            this.zLoc = (Expression<Number>) expressions[2];
        } else if (expressionUsed == 1) {
            this.location = (Expression<Location>) expressions[0];
        }
        return true;
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        NoiseManager noiseManager = SkNoise.instance.getNoiseManager();
        double x = 0, y = 0, z = 0;
        if (expressionUsed == 0) {
            if (xLoc == null && yLoc == null && zLoc == null) return null;
            x = xLoc.getSingle(event).doubleValue();
            y = yLoc == null ? 0 : yLoc.getSingle(event).doubleValue();
            z = zLoc == null ? 0 : zLoc.getSingle(event).doubleValue();
        } else if (expressionUsed == 1) {
            if (location == null) return null;
            Location loc = location.getSingle(event);
            if (loc == null) return null;
            x = loc.getX();
            y = loc.getY();
            z = loc.getZ();

        }
        return new Double[]{noiseManager.getSimplex().noise(x, y, z)};
    }


}
