package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a negation mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a unary expression implementation, where the negation operation
 * (unary minus) is applied to the result of another encapsulated expression. It
 * evaluates the negative of the interpreted value derived from the provided
 * sub-expression and the given context.
 *
 * Responsibilities:
 * - Computes the negation of the result of the nested expression's interpretation.
 *
 * Design:
 * - Utilizes a single sub-expression, provided during construction, to compute the result.
 * - The sub-expression must implement the `Expression` interface, allowing composition of
 *   this expression with other expressions to form complex structures.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of the encapsulated
 *   sub-expression, applies the negation operation, and returns the computed result.
 */
@RequiredArgsConstructor
public class NegationExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return -expression.interpret(context);
    }
}
