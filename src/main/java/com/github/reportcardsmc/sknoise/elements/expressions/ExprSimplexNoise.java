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
import org.bukkit.util.noise.SimplexNoiseGenerator;

import javax.annotation.Nullable;

public class ExprSimplexNoise extends SimpleExpression<Double> {

    static {
        String[] patterns = {"[sknoise] simplex noise [at] [x] %number%[, ][[y] %-number%[, ][[z] %-number%]] [(1¦with octaves %-integer%[,] frequency %-number%[,] and amplitude %-number%)]",
                "[sknoise] simplex noise [at] %-location% [(1¦with octaves %-integer%[,] frequency %-number%[,] [and] amplitude %-number%)]"};
        Skript.registerExpression(ExprSimplexNoise.class, Double.class, ExpressionType.COMBINED, patterns);
    }

    private Expression<Number> xLoc;
    private Expression<Number> yLoc;
    private Expression<Number> zLoc;
    private Expression<Location> location;
    private Expression<Integer> octaves;
    private Expression<Number> frequency;
    private Expression<Number> amplitude;


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
        if (parseResult.mark == 1) {
            this.octaves = (Expression<Integer>) expressions[expressions.length - 3];
            this.frequency = (Expression<Number>) expressions[expressions.length - 2];
            this.amplitude = (Expression<Number>) expressions[expressions.length - 1];
        }
        return true;
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        NoiseManager noiseManager = SkNoise.instance.getNoiseManager();
        Number x = null, y = null, z = null;
        Integer o = null;
        Double f = null, a = null;
        if (this.xLoc == null) {
            if (this.location != null) {
                Location loc = this.location.getSingle(event);
                x = loc.getX();
                y = loc.getY();
                z = loc.getZ();
                if (x == null || y == null || z == null) return null;
            }
        } else x = xLoc.getSingle(event);
        if (yLoc != null) {
            y = yLoc.getSingle(event);
            if (zLoc != null) {
                z = zLoc.getSingle(event);
            }
        }
        if (octaves != null) {
            o = octaves.getSingle(event);
            f = frequency.getSingle(event).doubleValue();
            a = amplitude.getSingle(event).doubleValue();
            if (o == null || f == null || a == null) return null;
        }

        Double noise = null;
        SimplexNoiseGenerator simp = noiseManager.getSimplex();
        if (y == null && o == null) noise = simp.noise(x.doubleValue());
        if (z == null && o == null) noise = simp.noise(x.doubleValue(), y.doubleValue());
        if (z != null && o == null) noise = simp.noise(x.doubleValue(), y.doubleValue(), z.doubleValue());
        if (y == null && o != null) noise = simp.noise(x.doubleValue(), 0, 0, o, f, a);
        if (z == null && o != null) noise = simp.noise(x.doubleValue(), y.doubleValue(), 0, o, f, a);
        if (z != null && o != null) noise = simp.noise(x.doubleValue(), y.doubleValue(), z.doubleValue(), o, f, a);
        return new Double[]{noise};
    }


}
