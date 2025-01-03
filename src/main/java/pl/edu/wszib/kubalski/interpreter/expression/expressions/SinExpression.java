package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a sine mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a unary expression implementation, where the sine operation (sin)
 * is applied to the result of another encapsulated expression. It evaluates the sine of the
 * interpreted value derived from the provided sub-expression and the given context.
 *
 * Responsibilities:
 * - Evaluates the sine operation on the result of the nested expression's interpretation.
 *
 * Design:
 * - Utilizes a single sub-expression, provided during construction, to compute the result.
 * - Leverages the `Math.sin` function to calculate the sine of the interpreted value.
 * - The sub-expression must implement the `Expression` interface, allowing composition of
 *   this expression with other expressions to form complex structures.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of the encapsulated
 *   sub-expression, applies the sine operation, and returns the computed result.
 */
@RequiredArgsConstructor
public class SinExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return Math.sin(expression.interpret(context));
    }
}
