package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a logarithmic mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a unary expression implementation, where the natural logarithm operation (log)
 * is applied to the result of another encapsulated expression. It evaluates the logarithm of the
 * interpreted value derived from the provided sub-expression and the given context.
 *
 * Responsibilities:
 * - Evaluates the logarithm operation on the result of the nested expression's interpretation.
 *
 * Design:
 * - Utilizes a single sub-expression, supplied during construction, to compute the result.
 * - Leverages the `Math.log` function to calculate the natural logarithm of the interpreted value.
 * - The sub-expression must implement the `Expression` interface, enabling composition of
 *   this expression with other expressions to form complex mathematical constructs.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of the encapsulated
 *   sub-expression, applies the logarithm operation, and returns the computed result.
 */
@RequiredArgsConstructor
public class LogExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return Math.log(expression.interpret(context));
    }
}
