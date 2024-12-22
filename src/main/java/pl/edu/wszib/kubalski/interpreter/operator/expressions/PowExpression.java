package pl.edu.wszib.kubalski.interpreter.operator.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.operator.Expression;

@RequiredArgsConstructor
public class PowExpression implements Expression {
    @NonNull
    private final Expression left;
    @NonNull
    private final Expression right;

    @Override
    public Double interpret(Context context) {
        return Math.pow(left.interpret(context), right.interpret(context));
    }
}
