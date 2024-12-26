package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

public class CtanExpression implements Expression {
    @NonNull
    private final Expression expression;

    public CtanExpression(@NonNull Expression expression) {
        this.expression = new TanExpression(expression);
    }

    @Override
    public Double interpret(Context context) {
        Double tanResult = this.expression.interpret(context);
        if (tanResult == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return 1 / tanResult;
    }
}
