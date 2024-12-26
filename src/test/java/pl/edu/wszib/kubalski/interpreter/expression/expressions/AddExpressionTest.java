package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddExpressionTest {

    /**
     * Tests the AddExpression class, which represents an addition operation
     * of two sub-expressions. The `interpret` method takes a context and
     * computes the sum of the interpreted results of its left and right expressions.
     */

    @Test
    void shouldEvaluateExpressionWithTwoPositiveNumbers() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(5.0);
        when(right.interpret(context)).thenReturn(3.0);

        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(8.0, result);
    }

    @Test
    void shouldEvaluateExpressionWithPositiveAndNegativeNumber() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(10.0);
        when(right.interpret(context)).thenReturn(-4.0);

        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(6.0, result);
    }

    @Test
    void shouldEvaluateExpressionWithTwoNegativeNumbers() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(-7.0);
        when(right.interpret(context)).thenReturn(-3.0);

        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(-10.0, result);
    }

    @Test
    void shouldEvaluateExpressionWithZeroAndPositiveNumber() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(0.0);
        when(right.interpret(context)).thenReturn(5.0);

        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void shouldEvaluateExpressionWithZeroAndNegativeNumber() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(0.0);
        when(right.interpret(context)).thenReturn(-5.0);

        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(-5.0, result);
    }
}
