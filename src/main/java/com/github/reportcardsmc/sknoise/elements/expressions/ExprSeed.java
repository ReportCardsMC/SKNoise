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

public class ExprSeed extends SimpleExpression<Number> {

    static {
        String[] patterns = {"perlin [noise] seed", "simplex [noise] seed"};
        Skript.registerExpression(ExprSeed.class, Number.class, ExpressionType.COMBINED, patterns);
    }
    private NoiseManager noiseManager = SkNoise.instance.getNoiseManager();
    private NoiseType type;

    @Override
    protected Number[] get(Event event) {
        Number number = noiseManager.getSeed(type);
        return new Number[]{number};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Perlin seed expression";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (i == 0) type = NoiseType.PERLIN;
        if (i == 1) type = NoiseType.SIMPLEX;
        return true;

    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Number seed = noiseManager.getSeed(type);
        Number setSeed;
        Integer intSeed;
        try {
            setSeed = (Number) delta[0];
            intSeed = setSeed.intValue();
        } catch (Exception ex) {
            return;
        }
        if (mode == Changer.ChangeMode.SET) {
            noiseManager.setSeed(type, intSeed);
        } else if (mode == Changer.ChangeMode.ADD) {
            noiseManager.setSeed(type,  seed.intValue() + (intSeed));
        } else if (mode == Changer.ChangeMode.REMOVE) {
            noiseManager.setSeed(type, seed.intValue() - (intSeed));
        } else if (mode == Changer.ChangeMode.RESET) {
            noiseManager.setSeed(type, 0);
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
