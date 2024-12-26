package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a division mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a binary expression implementation, where the division operation is performed
 * between two encapsulated sub-expressions. It calculates the result of dividing the interpreted
 * value of the right-hand expression by the interpreted value of the left-hand expression.
 *
 * Responsibilities:
 * - Performs a division operation using the results of the two encapsulated expressions.
 * - Ensures that division by zero is avoided by validating the left-hand expression's result.
 *
 * Design:
 * - Accepts two sub-expressions (left and right) provided during construction to compute the result.
 * - The left-hand expression represents the divisor.
 * - The right-hand expression represents the dividend.
 * - Throws an `ArithmeticException` when the interpreted value of the left-hand expression equals zero.
 * - Both sub-expressions must implement the `Expression` interface, allowing their composition
 *   with other expressions to form complex structures.
 *
 * Methods:
 * - `interpret(Context context)`: Evaluates both the left and right expressions, performs the division
 *   operation, and returns the resulting value.
 */
@RequiredArgsConstructor
public class DivideExpression implements Expression {
    @NonNull
    private final Expression left;
    @NonNull
    private final Expression right;


    @Override
    public Double interpret(Context context) {
        Double leftValue = left.interpret(context);
        if (leftValue == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return right.interpret(context) / leftValue;
    }
}
