package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Represents a mathematical constant expression for the base of natural logarithms (E)
 * in the context of the Interpreter design pattern.
 *
 * This class is a specialized implementation of the Expression interface that always
 * evaluates to the mathematical constant E (~2.718). It is a leaf node in the
 * expression tree used for interpretation.
 *
 * Responsibilities:
 * - Provides the constant value E when evaluated.
 *
 * Design:
 * - Implements the interpret method to return the constant value without any
 *   dependency on the provided context.
 * - Does not utilize any nested expressions or contextual data, acting as a
 *   standalone constant in the expression hierarchy.
 *
 * Methods:
 * - `interpret(Context context)`: Returns the constant value E without
 *   performing any calculations or requiring the context parameter.
 */
public class EConstantExpression implements Expression {
    @Override
    public Double interpret(Context context) {
        return Math.E;
    }
}
