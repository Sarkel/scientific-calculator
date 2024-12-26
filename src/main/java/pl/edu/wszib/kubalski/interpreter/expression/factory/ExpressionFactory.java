package pl.edu.wszib.kubalski.interpreter.expression.factory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionType;
import pl.edu.wszib.kubalski.interpreter.expression.expressions.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * Factory for creating specific expressions based on provided operator and operands.
 * The factory utilizes an internal store to delegate the creation of various types of expressions.
 *
 * The expressions supported include:
 * - Unary expressions such as negation.
 * - Functional expressions like logarithmic or square root computations.
 * - Binary expressions for arithmetic operations like addition, subtraction, multiplication, and division.
 * - Constant expressions such as mathematical constants (e.g., pi or e).
 *
 * The creation process matches the provided operator and operand count to an expression type.
 * In case of invalid input, a {@code NumberExpression} is returned as a fallback.
 *
 * The factory determines the specific expression type by:
 * - Comparing the operator string with predefined operators in the {@code ExpressionType} enumeration.
 * - Matching the number of arguments required by the operator against the provided operands.
 * - Accessing the appropriate factory from the store to create the corresponding expression instance.
 *
 * Throws {@code IllegalArgumentException} if the operator or operand configuration is invalid
 * and cannot be resolved to an expression type.
 */
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
