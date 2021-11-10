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

@Name("Octave Count of Generator")
@Description("Get/Change the octaves of a generator (Generator must have a fractal type active)")
@Examples({"set octave count of {_gen} to 4", "add 1 to octave count of {_gen}"})
public class OctavesExpr extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(OctavesExpr.class, Integer.class, ExpressionType.COMBINED, "[sknoise] octave[s] [count] of %noisegenerator%");
    }

    Expression<NoiseGenerator> generatorExpression;

    @Override
    protected @Nullable
    Integer[] get(Event e) {
        if (generatorExpression.getSingle(e) == null || generatorExpression.getSingle(e).mFractalType == FractalType.None) return null;
        return new Integer[]{generatorExpression.getSingle(e).mOctaves};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Octave Count of generator: " + generatorExpression.toString(e, debug);
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
        Number number = (Number) delta[0];
        NoiseGenerator generator = generatorExpression.getSingle(e);
        if (generator == null || number == null || generator.mFractalType == FractalType.None) return;
        int num = number.intValue();
        switch(mode) {
            case SET:
                generator.SetFractalOctaves(num);
                break;
            case RESET:
                generator.SetFractalOctaves(1);
                break;
            case ADD:
                generator.SetFractalOctaves(generator.mOctaves+num);
                break;
            case REMOVE:
                generator.SetFractalOctaves(generator.mOctaves-num);
                break;
        }
    }
}
