package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a square root mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a unary expression implementation, where the square root operation (sqrt)
 * is applied to the result of another encapsulated expression. It evaluates the square root
 * of the interpreted value derived from the provided sub-expression and the given context.
 *
 * Responsibilities:
 * - Evaluates the square root operation on the result of the nested expression's interpretation.
 * - Validates that the input to the square root operation is non-negative.
 *
 * Design:
 * - Utilizes a single sub-expression, provided during construction, to compute the result.
 * - Leverages the `Math.sqrt` function to calculate the square root of the interpreted value.
 * - Throws an `ArithmeticException` if the evaluated input value is negative.
 * - The sub-expression must implement the `Expression` interface, allowing composition of
 *   this expression with other expressions to form complex structures.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of the encapsulated
 *   sub-expression, applies the square root operation, and returns the computed result.
 */
@RequiredArgsConstructor
public class SqrtExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        Double value = expression.interpret(context);

        if (value < 0) {
            throw new ArithmeticException("Argument cannot be negative");
        }

        return Math.sqrt(value);
    }
}
