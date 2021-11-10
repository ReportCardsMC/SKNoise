package com.github.reportcardsmc.sknoise.elements.expressions.noise;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.github.reportcardsmc.sknoise.utilities.enums.FractalType;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Lacunarity of Generator")
@Description("Get/Change the lacunarity of a generator (Generator must have a fractal type active)")
@Examples({"set lacunarity of {_gen} to 0.05", "add 0.5 to lacunarity of {_gen}"})
public class LacunarityExpr extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(LacunarityExpr.class, Number.class, ExpressionType.COMBINED, "[sknoise] lacunarity of %noisegenerator%");
    }

    Expression<NoiseGenerator> generatorExpression;

    @Override
    protected @Nullable
    Number[] get(Event e) {
        if (generatorExpression.getSingle(e) == null || generatorExpression.getSingle(e).mFractalType.equals(FractalType.None)) return null;
        return new Number[]{generatorExpression.getSingle(e).mLacunarity};
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
        return "Lacunarity of generator: " + generatorExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        generatorExpression = (Expression<NoiseGenerator>) exprs[0];
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE) ? CollectionUtils.array(Number.class) : null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Number num = (Number) delta[0];
        NoiseGenerator generator = generatorExpression.getSingle(e);
        if (generator == null || num == null || generatorExpression.getSingle(e).mFractalType.equals(FractalType.None)) return;
        switch(mode) {
            case SET:
                generator.SetFractalLacunarity(num.floatValue());
                break;
            case RESET:
                generator.SetFractalLacunarity(2.0f);
                break;
            case ADD:
                generator.SetFractalLacunarity(generator.mLacunarity + num.floatValue());
                break;
            case REMOVE:
                generator.SetFractalLacunarity(generator.mLacunarity - num.floatValue());
                break;
        }
    }
}
