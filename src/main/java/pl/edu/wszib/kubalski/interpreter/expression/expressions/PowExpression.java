package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a power mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a binary expression implementation, where the power operation (exponentiation)
 * is performed between two encapsulated sub-expressions. It evaluates the result of raising
 * the value of the left-hand expression to the power of the value of the right-hand expression.
 *
 * Responsibilities:
 * - Performs the exponentiation operation using the results of the two nested expressions.
 *
 * Design:
 * - Requires two sub-expressions (left and right) to compute the result.
 * - Utilizes the `Math.pow` function to perform the power operation.
 * - Both sub-expressions must implement the `Expression` interface, allowing composition of
 *   this expression with other expressions to form complex structures.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of both the left-hand and
 *   right-hand expressions, applies the power operation, and returns the computed result.
 */
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
