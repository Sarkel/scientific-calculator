package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

@RequiredArgsConstructor
public class SubtractExpression implements Expression {
    @NonNull
    private final Expression left, right;

    @Override
    public Double interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}
