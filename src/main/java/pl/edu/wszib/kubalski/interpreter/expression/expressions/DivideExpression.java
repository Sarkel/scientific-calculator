package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

@RequiredArgsConstructor
public class DivideExpression implements Expression {
    @NonNull
    private final Expression left;
    @NonNull
    private final Expression right;


    @Override
    public Double interpret(Context context) {
        return right.interpret(context) / left.interpret(context);
    }
}
