package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;


@RequiredArgsConstructor
public class SqrtExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return Math.sqrt(expression.interpret(context));
    }
}
