package com.github.reportcardsmc.sknoise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class ExprAverage extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprAverage.class, Number.class, ExpressionType.PROPERTY, "(average|avg) of %numbers%");
    }

    Expression<Number> numbers;

    @Override
    protected Number[] get(Event event) {
        Number[] numbersToCheck = numbers.getArray(event);
        Number result;
        double sum = 0D;
        for (Number n : numbersToCheck) {
            sum += n.doubleValue();
        }
        result = sum / numbersToCheck.length;
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
    public String toString(Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        numbers = (Expression<Number>) expressions[0];
        return true;
    }
}
