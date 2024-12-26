package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a positive unary mathematical expression in the context of the Interpreter design pattern.
 *
 * This class is a unary expression implementation, where the result of the
 * nested expression is returned as a positive number. In essence, it evaluates the
 * result of the encapsulated sub-expression and ensures it is represented as positive.
 *
 * Responsibilities:
 * - Evaluates the result of the nested expression and returns it as positive.
 * - Ensures that the expression can be used in the context of larger compositions.
 *
 * Design:
 * - Utilizes a single sub-expression, provided during construction, to compute the result.
 * - Applies the unary positive operator (`+`) to the interpreted value of the sub-expression.
 * - Allows for composition with other expressions to form more complex evaluation logic.
 *
 * Methods:
 * - `interpret(Context context)`: Invokes the interpretation of the encapsulated
 *   sub-expression, applies the unary positive operator, and returns the computed result.
 */
@RequiredArgsConstructor
public class PositiveExpression implements Expression {
    @NonNull
    private final Expression expression;

    @Override
    public Double interpret(Context context) {
        return +expression.interpret(context);
    }
}
