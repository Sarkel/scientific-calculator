package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents an addition mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a binary expression implementation, where an addition operation (+)
 * is performed between two encapsulated sub-expressions. It evaluates the sum of the interpreted
 * values derived from the left-hand and right-hand expressions using the given context.
 *
 * Responsibilities:
 * - Performs the addition operation using the results of two nested expressions.
 *
 * Design:
 * - Requires two sub-expressions (left and right) to compute the result.
 * - Both sub-expressions must implement the `Expression` interface, enabling composition
 *   of this expression with other expressions to form complex operations.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of both the left-hand and
 *   right-hand expressions, applies the addition operation, and returns the computed result.
 */
@RequiredArgsConstructor
public class AddExpression implements Expression{
    @NonNull
    private final Expression left;
    @NonNull
    private final Expression right;

    @Override
    public Double interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}
