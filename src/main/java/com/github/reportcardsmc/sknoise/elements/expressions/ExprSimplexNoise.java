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
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;
import org.jetbrains.annotations.Nullable;

public class ExprSimplexNoise extends SimpleExpression<Double> {

    static {
        String[] patterns = {
                "[sknoise] simplex [noise] at [x] %number%[,] [[y] %-number%[(,|[,] and) [z] %-number%]] [(1¦with octaves %-number%[,] frequency %-number%(,|[,] and) amplitude %-number%)]",
                "[sknoise] simplex [noise] at (loc|location) %location% [(1¦with octaves %-integer%[,] frequency %-number%(,|[,] and) amplitude %-number%)]",
                "[sknoise] normalized simplex [noise] at [x] %number%[,] [[y] %-number%[(,|[,] and) [z] %-number%]] [(1¦with octaves %-double%[,] frequency %-number%(,|[,] and) amplitude %-number%)]",
                "[sknoise] normalized simplex [noise] at (loc|location) %location% [(1¦with octaves %-integer%[,] frequency %-number%(,|[,] and) amplitude %-number%)]"
        };
        Skript.registerExpression(ExprSimplexNoise.class, Double.class, ExpressionType.COMBINED, patterns);
    }

    private Expression<Number> xLoc;
    private Expression<Number> yLoc;
    private Expression<Number> zLoc;
    private Expression<Location> location;
    private Expression<Number> octaves;
    private Expression<Number> frequency;
    private Expression<Number> amplitude;
    private Boolean normalized = false;


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
        if (i == 0) {
            this.xLoc = (Expression<Number>) expressions[0];
            this.yLoc = (Expression<Number>) expressions[1];
            this.zLoc = (Expression<Number>) expressions[2];
        } else if (i == 1) {
            this.location = (Expression<Location>) expressions[0];
        } else if (i == 2) {
            this.xLoc = (Expression<Number>) expressions[0];
            this.yLoc = (Expression<Number>) expressions[1];
            this.zLoc = (Expression<Number>) expressions[2];
            this.normalized = true;
        } else if (i == 3) {
            this.location = (Expression<Location>) expressions[0];
            this.normalized = true;
        }
        if (parseResult.mark == 1) {
            this.octaves = (Expression<Number>) expressions[expressions.length - 3];
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
            try{
                Double ogOctaves = octaves.getSingle(event).doubleValue();

                if (ogOctaves > 8) o = 8;
                else if (ogOctaves < 1) o = 1;
                else {
                    o = new Double(Math.floor(ogOctaves.intValue())).intValue();
                }
            } catch (NullPointerException ignored) {
                o = 1;
            }
            f = frequency.getSingle(event).doubleValue();
            a = amplitude.getSingle(event).doubleValue();
            if (o == null || f == null || a == null) return null;
        }

        Double noise = null;
        SimplexNoiseGenerator simp = noiseManager.getSimplex();
        if (y == null && o == null) noise = simp.noise(x.doubleValue(), 1, 1, 1, this.normalized);
        else if (z == null && o == null) noise = simp.noise(x.doubleValue(), y.doubleValue(), 1, 1, 1, this.normalized);
        else if (z != null && o == null) noise = simp.noise(x.doubleValue(), y.doubleValue(), z.doubleValue(), 1, 1, 1, this.normalized);
        else if (y == null && o != null) noise = simp.noise(x.doubleValue(), 0, 0, o, f, a, this.normalized);
        else if (z == null && o != null) noise = simp.noise(x.doubleValue(), y.doubleValue(), 0, o, f, a, this.normalized);
        else if (z != null && o != null) noise = simp.noise(x.doubleValue(), y.doubleValue(), z.doubleValue(), o, f, a, this.normalized);
        return new Double[]{noise};
    }

}

