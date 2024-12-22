package pl.edu.wszib.kubalski.interpreter.operator.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.operator.Expression;

@RequiredArgsConstructor
public class TanExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return Math.tan(expression.interpret(context));
    }
}