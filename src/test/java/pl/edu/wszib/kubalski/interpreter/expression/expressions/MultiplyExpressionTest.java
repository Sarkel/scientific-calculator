package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyExpressionTest {

    /**
     * Test class for the MultiplyExpression.
     * This class tests the `interpret` method, which calculates the product
     * of two expressions by interpreting their values in the given context.
     */

    @Test
    void shouldReturnProduct_WhenBothNumbersArePositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(5.0);
        Mockito.when(right.interpret(context)).thenReturn(3.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(15.0, result);
    }

    @Test
    void shouldReturnProduct_WhenBothNumbersAreNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(-4.0);
        Mockito.when(right.interpret(context)).thenReturn(-2.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(8.0, result);
    }

    @Test
    void shouldReturnProduct_WhenOneNumberIsPositiveAndOneIsNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(-6.0);
        Mockito.when(right.interpret(context)).thenReturn(2.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(-12.0, result);
    }

    @Test
    void shouldReturnZero_WhenOneNumberIsZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(7.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void shouldReturnZero_WhenBothNumbersAreZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(0.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }
}
