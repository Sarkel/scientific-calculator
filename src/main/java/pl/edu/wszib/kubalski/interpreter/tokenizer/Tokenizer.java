package pl.edu.wszib.kubalski.interpreter.tokenizer;

import java.util.List;

/**
 * The Tokenizer interface defines the contract for tokenizing a mathematical
 * or logical expression represented as a string into a list of string tokens.
 *
 * Implementations of this interface should handle various components of the
 * expression such as numbers, operators, functions, parentheses, and any
 * other elements that are required to parse and evaluate the expression
 * correctly.
 *
 * This interface is instrumental in the interpretation process as it transforms
 * the raw input string into discrete, manageable parts that can be used by
 * subsequent components like parsers and evaluators.
 *
 * Responsibilities:
 * - Define the method to tokenize an expression string.
 * - Provide an extensible foundation for different types of tokenization logic.
 *
 * Primary use case:
 * - Input a string expression (e.g., "3 + 5 * (2 - 4)").
 * - Produce a list of tokens (e.g., ["3", "+", "5", "*", "(", "2", "-", "4", ")"]).
 */
public interface Tokenizer {
    List<String> tokenize(String expression);
}
