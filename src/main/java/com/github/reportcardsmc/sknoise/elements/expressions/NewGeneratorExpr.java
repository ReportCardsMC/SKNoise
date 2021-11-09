package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.utilities.enums.ValidGenerators;
import com.github.reportcardsmc.sknoise.utilities.noise.Cellular;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import com.github.reportcardsmc.sknoise.utilities.noise.Perlin;
import com.github.reportcardsmc.sknoise.utilities.noise.Simplex;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class NewGeneratorExpr extends SimpleExpression<NoiseGenerator> {

    static {
        Skript.registerExpression(NewGeneratorExpr.class, NoiseGenerator.class, ExpressionType.COMBINED, "[sknoise] new %generatortype% [noise] generator [with seed %-number%]");
    }

    Expression<ValidGenerators> generatorsExpression;
    Expression<Number> numberExpression;

    @Override
    protected @Nullable
    NoiseGenerator[] get(Event e) {
        ValidGenerators gen = generatorsExpression.getSingle(e);
        Bukkit.getLogger().info("Gen type: " + generatorsExpression.toString(e, true));
        if (gen == null) return null;
        int seed = (numberExpression == null || numberExpression.getSingle(e) == null) ? new Random().nextInt() : numberExpression.getSingle(e).intValue();
        switch (gen) {
            case PERLIN:
                return new NoiseGenerator[]{new Perlin(seed)};
            case SIMPLEX:
                return new Simplex[]{new Simplex(seed)};
            case CELLULAR:
                return new Cellular[]{new Cellular(seed)};
            default:
                return null;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends NoiseGenerator> getReturnType() {
        return NoiseGenerator.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "New generator with type: " + generatorsExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        generatorsExpression = (Expression<ValidGenerators>) exprs[0];
        numberExpression = (Expression<Number>) exprs[1];
        return true;
    }
}
