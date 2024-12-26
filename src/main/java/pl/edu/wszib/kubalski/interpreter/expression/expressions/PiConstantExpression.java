package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents the mathematical constant π (pi) in the context of the Interpreter design pattern.
 *
 * This class is a constant expression implementation, which provides the value of π
 * as defined by the `Math.PI` constant. The interpretation of this expression always evaluates
 * to the constant value of π, independent of any provided context.
 *
 * Responsibilities:
 * - Returns the constant value of π during interpretation.
 *
 * Design:
 * - Implements the `Expression` interface, enabling its use in expression compositions.
 * - This class does not depend on or utilize the provided context.
 *
 * Methods:
 * - `interpret(Context context)`: Returns the predefined constant value `Math.PI`.
 */
public class PiConstantExpression implements Expression {
    @Override
    public Double interpret(Context context) {
        return Math.PI;
    }
}
