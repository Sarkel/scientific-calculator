package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a constant numeric expression in the context of the Interpreter design pattern.
 *
 * This class is a simple implementation of the Expression interface, designed to encapsulate
 * and interpret a numeric value provided as a string. It directly converts the string value
 * to a Double when evaluated, without depending on or affecting the provided context.
 *
 * Responsibilities:
 * - Encapsulates a numeric value in string format as a constant expression.
 * - Evaluates the constant by parsing the numeric string into a Double.
 *
 * Design:
 * - Does not depend on any nested expressions; it is a terminal or leaf node
 *   in the expression hierarchy.
 * - Does not utilize the `Context` during interpretation, as the encapsulated value is standalone.
 *
 * Methods:
 * - `interpret(Context context)`: Converts the stored numeric string to a Double
 *   and returns it as the result of the interpretation.
 */
@RequiredArgsConstructor
public class NumberExpression implements Expression {
    @NonNull
    private final String value;

    @Override
    public Double interpret(Context context) {
        return Double.parseDouble(value);
    }
}
