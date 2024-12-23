package pl.edu.wszib.kubalski.interpreter.expression;

import lombok.Getter;
import pl.edu.wszib.kubalski.interpreter.expression.expressions.*;

import java.util.Map;

@Getter
public class ExpressionFactoryStore {
    private final Map<ExpressionType, OneArgumentExpressionFactory> unaryExpressions = Map.ofEntries(
            Map.entry(ExpressionType.NEGATE, NegationExpression::new),
            Map.entry(ExpressionType.POSITIVE, PositiveExpression::new)
    );

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

    interface TwoArgumentExpressionFactory {
        Expression createExpression(Expression left, Expression right);
    }

    interface OneArgumentExpressionFactory {
        Expression createExpression(Expression expression);
    }

    interface ConstantExpressionFactory {
        Expression createExpression();
    }
}
