package pl.edu.wszib.kubalski.interpreter.operator.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.operator.Expression;

@RequiredArgsConstructor
public class SinExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return Math.sin(expression.interpret(context));
    }
}