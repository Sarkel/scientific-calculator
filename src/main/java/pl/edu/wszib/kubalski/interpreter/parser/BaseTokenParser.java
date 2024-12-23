package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryHelper;

import java.util.*;

public class BaseTokenParser extends TokenParser {
    private int position = 0;

    public BaseTokenParser(
            @NonNull List<String> tokens,
            @NonNull ExpressionFactoryHelper expressionFactoryHelper,
            @NonNull ExpressionFactory expressionFactory
    ) {
        super(tokens, expressionFactoryHelper, expressionFactory);
    }


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
