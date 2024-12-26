package pl.edu.wszib.kubalski.interpreter.expression;

import pl.edu.wszib.kubalski.interpreter.Context;

/**
 * The Expression interface represents a component in the Interpreter design pattern.
 * It defines a contract for evaluating or interpreting mathematical or logical constructs
 * based on a provided context. Implementations of this interface encapsulate specific
 * interpretation logic for various operations or constants.
 *
 * Responsibilities:
 * - Process and evaluate the expression using the given context.
 * - Define the mechanics of interpretation for specific operations, constants, or combinations thereof.
 *
 * Design:
 * - This interface can be extended to define unary, binary, or constant expressions.
 * - Enables the composition of nested expressions for creating complex evaluations.
 * - Contextual information required for interpretation is passed through the `Context` parameter.
 *
 * Implementing classes must define:
 * - How the interpretation is carried out.
 * - How the result of the expression is derived using contextual values.
 *
 * Example implementations include constant expressions, mathematical operations
 * (e.g., Addition, Subtraction, Multiplication), or unary operations (e.g., Negation).
 *
 * Methods:
 * - `interpret(Context context)`: Evaluates the expression, potentially using values or metadata
 *   provided by the `Context`, and returns the computed result as a Double.
 */
public interface Expression {
    Double interpret(Context context);
}
