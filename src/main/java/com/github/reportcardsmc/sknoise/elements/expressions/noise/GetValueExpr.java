package com.github.reportcardsmc.sknoise.elements.expressions.noise;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.reportcardsmc.sknoise.utilities.noise.NoiseGenerator;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Get Value of Generator")
@Description("Returns a value at a given location using the provided generator")
@Examples("value of {_generator} at player's location")
public class GetValueExpr extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(GetValueExpr.class, Number.class, ExpressionType.COMBINED,
                "[sknoise] value (of|using) %noisegenerator% at %location%",
                "[sknoise] value (of|using) %noisegenerator% at %number%[,] %number%[,] [and] %number%");
    }

    Expression<NoiseGenerator> generatorExpression;
    Expression<Location> locationExpression;
    Expression<Number> xExpr;
    Expression<Number> yExpr;
    Expression<Number> zExpr;
    int match;

    @Override
    protected @Nullable
    Number[] get(Event e) {
        NoiseGenerator generator = generatorExpression.getSingle(e);
        Number xNum = null;
        Number yNum = null;
        Number zNum = null;
        if (match == 0) {
            if (locationExpression.getSingle(e) == null) return null;
            xNum = locationExpression.getSingle(e).getX();
            yNum = locationExpression.getSingle(e).getY();
            zNum = locationExpression.getSingle(e).getZ();
        }
        else {
            xNum = xExpr.getSingle(e);
            yNum = yExpr.getSingle(e);
            zNum = zExpr.getSingle(e);
        }
        if (generator == null || xNum == null || yNum == null || zNum == null) return null;
        return new Number[]{(generator).GetNoise(xNum.floatValue(), yNum.floatValue(), zNum.floatValue())};
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
        return "Get value of gen: " + generatorExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        generatorExpression = (Expression<NoiseGenerator>) exprs[0];
        match = matchedPattern;
        if (matchedPattern == 0) {
            locationExpression = (Expression<Location>) exprs[1];
        } else if (matchedPattern == 1) {
            xExpr = (Expression<Number>) exprs[1];
            yExpr = (Expression<Number>) exprs[2];
            zExpr = (Expression<Number>) exprs[3];
        }
        return true;
    }
}
