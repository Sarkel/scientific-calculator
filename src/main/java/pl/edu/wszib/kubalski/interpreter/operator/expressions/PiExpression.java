package pl.edu.wszib.kubalski.interpreter.operator.expressions;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.operator.Expression;

public class PiExpression implements Expression {
    @Override
    public Double interpret(Context context) {
        return Math.PI;
    }
}
