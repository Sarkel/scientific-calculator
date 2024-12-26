package pl.edu.wszib.kubalski.interpreter.expression;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.expressions.*;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public class ExpressionFactory {
    @NonNull
    private final ExpressionFactoryStore store;

    public Expression createExpression(String operator, Expression left, Expression right) {
        try {
            ExpressionType expressionType = Arrays.stream(ExpressionType.values())
                    .filter(et -> Objects.equals(et.getOperator(), operator))
                    .filter(et -> et.getNumberOfArguments() == (left == null ? 0 : 1) + (right == null ? 0 : 1))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid operator or value: " + operator));

            if (store.getUnaryExpressions().containsKey(expressionType)) {
                return store.getUnaryExpressions().get(expressionType).createExpression(left);
            }
            if (store.getFunctionalExpressions().containsKey(expressionType)) {
                return store.getFunctionalExpressions().get(expressionType).createExpression(left);
            }
            if (store.getLowPriorityExpressions().containsKey(expressionType)) {
                return store.getLowPriorityExpressions().get(expressionType).createExpression(left, right);
            }
            if (store.getHighPriorityExpressions().containsKey(expressionType)) {
                return store.getHighPriorityExpressions().get(expressionType).createExpression(left, right);
            }
            if (store.getConstantExpressions().containsKey(expressionType)) {
                return store.getConstantExpressions().get(expressionType).createExpression();
            }
        } catch (IllegalArgumentException e) {
            return new NumberExpression(operator);
        }

        throw new IllegalArgumentException("Invalid operator or value: " + operator);
    }
}
