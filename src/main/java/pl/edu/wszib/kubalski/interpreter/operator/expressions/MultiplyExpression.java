package pl.edu.wszib.kubalski.interpreter.operator.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.operator.Expression;

@RequiredArgsConstructor
public class MultiplyExpression implements Expression {
    @NonNull
    private final Expression left;
    @NonNull
    private final Expression right;

    @Override
    public Double interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
}