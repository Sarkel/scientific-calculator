package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a multiplication mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a binary expression implementation, where the multiplication operation
 * is performed between two encapsulated sub-expressions. It evaluates the product of
 * the interpreted results of both sub-expressions using the provided context.
 *
 * Responsibilities:
 * - Performs the multiplication operation using the results of the two nested expressions.
 *
 * Design:
 * - Requires two sub-expressions (left and right) to compute the result.
 * - Implements the `Expression` interface, allowing it to be combined with other expressions
 *   to form complex and nested structures.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of the left-hand and right-hand
 *   expressions, computes their product, and returns the resulting value as a Double.
 */
@RequiredArgsConstructor
public class MultiplyExpression implements Expression {
    @NonNull
    private final Expression left;
    @NonNull
    private final Expression right;

    @Override
    public Double interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
}
