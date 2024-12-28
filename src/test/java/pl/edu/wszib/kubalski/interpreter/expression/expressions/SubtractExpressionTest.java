package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubtractExpressionTest {

    /**
     * Tests for SubtractExpression class.
     * <p>
     * The SubtractExpression class represents a mathematical subtraction operation between two expressions.
     * The method being tested, `interpret`, computes the result of subtracting the interpreted value of
     * the `right` expression from the `left` expression.
     */

    @Test
    void shouldReturnCorrectResult_whenBothExpressionsArePositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(10.0);
        when(right.interpret(context)).thenReturn(5.0);

        SubtractExpression subtractExpression = new SubtractExpression(left, right);

        // Act
        Double result = subtractExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void shouldReturnCorrectResult_whenBothExpressionsAreNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(-10.0);
        when(right.interpret(context)).thenReturn(-5.0);

        SubtractExpression subtractExpression = new SubtractExpression(left, right);

        // Act
        Double result = subtractExpression.interpret(context);

        // Assert
        assertEquals(-5.0, result);
    }

    @Test
    void shouldReturnZero_whenBothExpressionsAreZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(0.0);
        when(right.interpret(context)).thenReturn(0.0);

        SubtractExpression subtractExpression = new SubtractExpression(left, right);

        // Act
        Double result = subtractExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void shouldReturnPositiveResult_whenLeftExpressionIsGreaterThanRight() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(15.0);
        when(right.interpret(context)).thenReturn(3.0);

        SubtractExpression subtractExpression = new SubtractExpression(left, right);

        // Act
        Double result = subtractExpression.interpret(context);

        // Assert
        assertEquals(12.0, result);
    }

    @Test
    void shouldReturnNegativeResult_whenLeftExpressionIsLessThanRight() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(3.0);
        when(right.interpret(context)).thenReturn(15.0);

        SubtractExpression subtractExpression = new SubtractExpression(left, right);

        // Act
        Double result = subtractExpression.interpret(context);

        // Assert
        assertEquals(-12.0, result);
    }
}
