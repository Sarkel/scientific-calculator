package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.util.List;



/**
 * The TokenParser class is responsible for parsing a list of tokens and constructing
 * an abstract syntax tree (AST) representing mathematical or logical expressions.
 * It supports parsing expressions with various precedence levels and different
 * types of operators such as unary, binary, and functional.
 *
 * The class uses an ExpressionFactory and ExpressionFactoryHelper to assist in
 * creating and managing expressions based on tokenized input.
 *
 * Responsibilities:
 * - Parsing tokens into structured expressions.
 * - Managing operator precedence to correctly build the abstract syntax tree.
 * - Validating token syntax and structure during parsing.
 *
 * Design:
 * - Implements recursive-descent parsing to support precedence and associativity.
 * - Divides parsing into multiple levels, e.g., expressions, terms, and factors, to
 *   handle different operator precedences.
 * - Uses utility methods to match, check, and advance tokens during parsing.
 * - Encapsulates token handling and parsing logic within private helper methods.
 *
 * Methods:
 * - `parse`: Entry point to parse the tokens and return the resulting Expression.
 * - `parseExpression`: Handles parsing low-priority operators (e.g., addition and subtraction).
 * - `parseTerm`: Handles parsing high-priority operators (e.g., multiplication and division).
 * - `parseFactor`: Handles parsing of individual operands, parentheses, and unary operations.
 * - Utility methods (`match`, `expect`, `advance`, etc.) for token manipulation and validation.
 *
 * Dependencies:
 * - ExpressionFactory: Responsible for creating concrete expression instances.
 * - ExpressionFactoryHelper: Provides sets of operators categorized by precedence or functionality.
 * - Expression: Represents individual nodes in the syntax tree.
 *
 * Usage Considerations:
 * - Throws IllegalArgumentException if the token stream contains invalid syntax or unsupported tokens.
 * - Relies on the token list's correct initialization and expected formatting.
 */
@RequiredArgsConstructor
public class TokenParser {
    @NonNull
    private final List<String> tokens;

    @NonNull
    private final ExpressionFactoryHelper expressionFactoryHelper;

    @NonNull
    private final ExpressionFactory expressionFactory;

    private int position = 0;

    public Expression parse() {
        return parseExpression();
    }

    // Parse an expression (handles lowest-precedence operators)
    private Expression parseExpression() {
        Expression left = parseTerm();
        while (match(expressionFactoryHelper.getLowPriorityExpressions())) {
            String operator = previous();
            Expression right = parseTerm();

            left = expressionFactory.createExpression(operator, left, right);
        }
        return left;
    }

    // Parse a term (handles higher-precedence operators: * and /)
    private Expression parseTerm() {
        Expression left = parseFactor();
        while (match(expressionFactoryHelper.getHighPriorityExpressions())) {
            String operator = previous();
            Expression right = parseFactor();

            left = expressionFactory.createExpression(operator, left, right);
        }
        return left;
    }

    // Parse a factor (handles parentheses, numbers, and functions)
    private Expression parseFactor() {
        // Check for unary operators '+' and '-'
        if (match(expressionFactoryHelper.getUnaryExpressions())) {
            String operator = previous();
            Expression expression = parseFactor();
            return expressionFactory.createExpression(operator, expression, null);
        }

        if (match("(")) {
            Expression expression = parseExpression();
            expect(")"); // Ensure closing parenthesis
            return expression;
        }
        if (match(expressionFactoryHelper.getFunctionalExpressions())) {
            String function = previous();
            expect("(");
            Expression argument = parseExpression();
            expect(")");

            return expressionFactory.createExpression(function, argument, null);
        }
        if (match(expressionFactoryHelper.getConstantExpressions()) || matchNumber()) {
            String terminalValue = previous();

            return expressionFactory.createExpression(terminalValue, null, null);
        }

        throw new IllegalArgumentException("Unexpected token: " + peek());
    }

    // Utility functions for parsing
    private boolean match(String ...expected) {
        for (String token : expected) {
            if (check(token)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean matchNumber() {
        try {
            Double.parseDouble(peek());
            advance();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void expect(String token) {
        if (!match(token)) {
            throw new IllegalArgumentException("Expected '" + token + "', found: " + peek());
        }
    }

    private String peek() {
        if (position >= tokens.size()) return "";
        return tokens.get(position);
    }

    private String previous() {
        return tokens.get(position - 1);
    }

    private boolean check(String token) {
        if (position >= tokens.size()) return false;
        return tokens.get(position).equals(token);
    }

    private void advance() {
        position++;
    }
}
