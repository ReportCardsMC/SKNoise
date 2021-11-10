package com.github.reportcardsmc.sknoise.elements.expressions.math;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class AverageExpr extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(AverageExpr.class, Number.class, ExpressionType.COMBINED, "[sknoise] av(erage|g) of %numbers%");
    }

    Expression<Number> numbers;

    @Override
    protected @Nullable
    Number[] get(Event e) {
        Number[] numbersToCheck = numbers.getArray(e);
        Number result;
        double sum = 0D;
        for (Number n : numbersToCheck) {
            sum += n.floatValue();
        }
        result = sum / (numbersToCheck.length == 0 ? 1 : numbersToCheck.length);
        return new Number[]{result};
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
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        numbers = (Expression<Number>) exprs[0];
        return true;
    }
}
