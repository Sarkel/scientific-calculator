package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a subtraction operation in the context of the Interpreter design pattern.
 *
 * This class is a binary expression implementation, where the result of subtracting
 * two sub-expressions is computed. It interprets the values of the left and right
 * sub-expressions using a specified context and performs a subtraction operation.
 *
 * Responsibilities:
 * - Evaluates two sub-expressions and computes their difference.
 *
 * Design:
 * - The `SubtractExpression` class requires two sub-expressions, provided during construction,
 *   which represent the left and right operands of the subtraction.
 * - Both sub-expressions must implement the `Expression` interface, allowing this class
 *   to be composed with other expressions to form complex computations.
 *
 * Methods:
 * - `interpret(Context context)`: Computes the difference between the interpreted values
 *   of the left and right sub-expressions and returns the result as a Double.
 */
@RequiredArgsConstructor
public class SubtractExpression implements Expression {
    @NonNull
    private final Expression left, right;

    @Override
    public Double interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}
