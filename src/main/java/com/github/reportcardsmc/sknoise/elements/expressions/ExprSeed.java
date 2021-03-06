package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.github.reportcardsmc.sknoise.SkNoise;
import com.github.reportcardsmc.sknoise.utilities.NoiseManager;
import com.github.reportcardsmc.sknoise.utilities.NoiseType;
import org.bukkit.event.Event;

public class ExprSeed extends SimpleExpression<Long> {

    static {
        String[] patterns = {"perlin [noise] seed", "simplex [noise] seed", "voronoi [noise] seed"};
        Skript.registerExpression(ExprSeed.class, Long.class, ExpressionType.COMBINED, patterns);
    }

    private final NoiseManager noiseManager = SkNoise.instance.getNoiseManager();
    private NoiseType type;

    @Override
    protected Long[] get(Event event) {
        long number = noiseManager.getSeed(type);
        return new Long[]{number};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return type.name() + " Seed Expression";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        switch (i) {
            case 0:
                type = NoiseType.PERLIN;
                break;
            case 1:
                type = NoiseType.SIMPLEX;
                break;
            case 2:
                type = NoiseType.CELLULAR;
                break;
        }
        return true;

    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        long seed = noiseManager.getSeed(type);
        long setSeed;
        long intSeed;
        try {
            setSeed = (long) delta[0];
            intSeed = setSeed;
        } catch (Exception ex) {
            return;
        }
        switch (mode) {
            case SET:
                noiseManager.setSeed(type, intSeed);
                break;
            case ADD:
                noiseManager.setSeed(type, seed + (intSeed));
                break;
            case REMOVE:
                noiseManager.setSeed(type, seed - (intSeed));
                break;
            case RESET:
                noiseManager.setSeed(type, 0);
                break;
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}
