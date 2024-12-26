package pl.edu.wszib.kubalski.interpreter;

import lombok.Builder;

/**
 * The Context class serves as a container for shared state or metadata
 * that may be required during the interpretation or evaluation of expressions.
 *
 * Its primary function is to facilitate communication and storage of
 * auxiliary data that expressions or the interpreter might depend on.
 *
 * This class is designed to be immutable, with its instances being created
 * using the Builder pattern provided by the `@Builder` annotation.
 *
 * Usage:
 * - Provides any relevant state or context to expressions during evaluation.
 * - Acts as a bridge for passing global or shared information across components
 *   (e.g., during mathematical expression evaluation).
 *
 * Typical working scenario:
 * - The Context is initialized once, typically at the start of evaluating an expression.
 * - It is passed onto components in the interpretation pipeline, such as expressions
 *   or the interpreter engine.
 *
 * Users are expected to configure Context properties using its builder prior to passing
 * it to dependent components like Parsers or Evaluators.
 */
@Builder
public class Context {
}
