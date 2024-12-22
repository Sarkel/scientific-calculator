package pl.edu.wszib.kubalski.interpreter.operator;

import pl.edu.wszib.kubalski.interpreter.operator.expressions.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ExpressionFactory {
    private final Map<ExpressionType, OneArgumentExpressionFactory> functionalExpressions = Map.ofEntries(
            Map.entry(ExpressionType.COS, CosExpression::new),
            Map.entry(ExpressionType.LOG, LogExpression::new),
            Map.entry(ExpressionType.SIN, SinExpression::new),
            Map.entry(ExpressionType.SQRT, SqrtExpression::new),
            Map.entry(ExpressionType.TAN, TanExpression::new)
    );

    private final Map<ExpressionType, TwoArgumentExpressionFactory> lowPriorityExpressions = Map.ofEntries(
            Map.entry(ExpressionType.ADD, AddExpression::new),
            Map.entry(ExpressionType.SUBTRACT, SubtractExpression::new)
    );

    private final Map<ExpressionType, TwoArgumentExpressionFactory> highPriorityExpressions = Map.ofEntries(
            Map.entry(ExpressionType.DIVIDE, DivideExpression::new),
            Map.entry(ExpressionType.MULTIPLY, MultiplyExpression::new),
            Map.entry(ExpressionType.POW, PowExpression::new)
    );

    private final Map<ExpressionType, ConstantExpressionFactory> constantExpressions = Map.ofEntries(
            Map.entry(ExpressionType.PI, PiExpression::new)
    );

    public Expression createExpression(String operator, Expression left, Expression right) {
        try {
            ExpressionType expressionType = Arrays.stream(ExpressionType.values())
                    .filter(et -> Objects.equals(et.getOperator(), operator))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid operator or value: " + operator));


            if (functionalExpressions.containsKey(expressionType)) {
                return functionalExpressions.get(expressionType).createExpression(left);
            }
            if (lowPriorityExpressions.containsKey(expressionType)) {
                return lowPriorityExpressions.get(expressionType).createExpression(left, right);
            }
            if (highPriorityExpressions.containsKey(expressionType)) {
                return highPriorityExpressions.get(expressionType).createExpression(left, right);
            }
            if (constantExpressions.containsKey(expressionType)) {
                return constantExpressions.get(expressionType).createExpression();
            }
        } catch (IllegalArgumentException e) {
            return new NumberExpression(operator);
        }

        throw new IllegalArgumentException("Invalid operator or value: " + operator);
    }

    public String[] getFunctionalExpressions() {
        return getExpressionTypes(functionalExpressions.keySet());
    }

    public String[] getLowPriorityExpressions() {
        return getExpressionTypes(lowPriorityExpressions.keySet());
    }

    public String[] getHighPriorityExpressions() {
        return getExpressionTypes(highPriorityExpressions.keySet());
    }

    public String[] getConstantExpressions() {
        return getExpressionTypes(constantExpressions.keySet());
    }

    private String[] getExpressionTypes(Set<ExpressionType> expressionTypes) {
        return expressionTypes.stream().map(ExpressionType::getOperator).toArray(String[]::new);
    }

    private interface TwoArgumentExpressionFactory {
        Expression createExpression(Expression left, Expression right);
    }

    private interface OneArgumentExpressionFactory {
        Expression createExpression(Expression expression);
    }

    private interface ConstantExpressionFactory {
        Expression createExpression();
    }
}
