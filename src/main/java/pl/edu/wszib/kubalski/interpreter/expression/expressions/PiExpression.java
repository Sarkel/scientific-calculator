package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

public class PiExpression implements Expression {
    @Override
    public Double interpret(Context context) {
        return Math.PI;
    }
}
