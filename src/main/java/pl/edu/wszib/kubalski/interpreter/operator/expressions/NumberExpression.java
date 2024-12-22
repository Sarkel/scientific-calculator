package pl.edu.wszib.kubalski.interpreter.operator.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.operator.Expression;


@RequiredArgsConstructor
public class NumberExpression implements Expression {
    @NonNull
    private final String value;

    @Override
    public Double interpret(Context context) {
        return Double.parseDouble(value);
    }
}
