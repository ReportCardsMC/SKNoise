package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.SkNoise;
import com.github.reportcardsmc.sknoise.utilities.Cellular;
import com.github.reportcardsmc.sknoise.utilities.NoiseManager;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprVoronoiNoise extends SimpleExpression<Double> {

    static {
        String[] patterns = {
                "[sknoise] voronoi [noise] at [x] %number%[,] [[y] %-number%[(,|[,] and) [z] %-number%]] [(1¦with cell values)]",
                "[sknoise] voronoi [noise] at (loc|location) %location% [(1¦with cell values)]"
        };
        Skript.registerExpression(ExprVoronoiNoise.class, Double.class, ExpressionType.COMBINED, patterns);
    }

    private Expression<Number> xLoc;
    private Expression<Number> yLoc;
    private Expression<Number> zLoc;
    private Expression<Location> location;
    private boolean returnCellValue;



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
        return "VORONOI WOAH";
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
        } else if (i == 3) {
            this.location = (Expression<Location>) expressions[0];
        }
        returnCellValue = false;
        if (parseResult.mark == 1) {
            returnCellValue = true;
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

        Double noise = null;
        Cellular voronoi = noiseManager.getCellular();
        if (returnCellValue) voronoi.setCellularReturnType(0);
        else if (!returnCellValue) voronoi.setCellularReturnType(2);
        if (y == null && o == null) noise = voronoi.getNoise(x.doubleValue(), 0);
        else if (z == null && o == null) noise = voronoi.getNoise(x.doubleValue(), y.doubleValue());
        else if (z != null && o == null) noise = voronoi.getNoise(x.doubleValue(), y.doubleValue(), z.doubleValue());
        return new Double[]{noise};
    }


}
