package pl.edu.wszib.kubalski.interpreter.expression;

import lombok.Getter;
import pl.edu.wszib.kubalski.interpreter.expression.expressions.*;

import java.util.Map;


/**
 * The ExpressionFactoryStore class is responsible for providing and managing various
 * factories for creating mathematical expression objects compliant with the Interpreter design pattern.
 * These factories allow the construction of unary, binary, and constant expression instances
 * based on the specified ExpressionType.
 *
 * This class serves as a registry for expression factories categorized by the type
 * and priority of the operation. It organizes the factories into maps for efficient
 * lookup and instantiation of expression objects.
 *
 * Features:
 * - Unary Expression Factories: Supports the creation of expressions requiring a single operand.
 *   Examples include negation and trigonometric operations such as cosine and sine.
 * - Functional Expression Factories: Enables expressions based on mathematical functions like logarithm and square root.
 * - Low Priority Expression Factories: Handles binary operations with lower precedence, such as addition and subtraction.
 * - High Priority Expression Factories: Handles binary operations with higher precedence, such as multiplication and division.
 * - Constant Expression Factories: Supports constants like π (Pi) and e.
 *
 * Responsibilities:
 * - Acts as a centralized point for instantiating various types of mathematical expressions.
 * - Provides a structured way to associate specific ExpressionType values with their respective
 *   expression factories.
 *
 * Design:
 * - The ExpressionFactoryStore uses the Map.ofEntries construct to initialize immutable maps for each category of factories.
 * - Three types of factories are defined:
 *   - OneArgumentExpressionFactory: Creates unary expressions requiring a single operand.
 *   - TwoArgumentExpressionFactory: Creates binary expressions requiring two operands.
 *   - ConstantExpressionFactory: Creates constant expressions with no operands.
 *
 * Usage:
 * - The ExpressionFactoryStore is utilized to efficiently retrieve the appropriate factory
 *   based on an ExpressionType, and then create the required Expression instance.
 *
 * Components:
 * - unaryExpressions: Map containing factories for unary mathematical operations.
 * - functionalExpressions: Map for creating functional unary expressions like cosine or square root.
 * - lowPriorityExpressions: Map for creating binary operations with low precedence (e.g., addition).
 * - highPriorityExpressions: Map for creating binary operations with high precedence (e.g., multiplication).
 * - constantExpressions: Map for creating constant expressions.
 */
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
            Map.entry(ExpressionType.PI, PiConstantExpression::new),
            Map.entry(ExpressionType.E, EConstantExpression::new)
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
