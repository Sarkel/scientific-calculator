package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SinExpressionTest {

    /**
     * This class tests the SinExpression class, which calculates the sine of the result of interpreting an inner Expression.
     */

    @Test
    void shouldCalculateSineOfZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(0.0);
        SinExpression sinExpression = new SinExpression(innerExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.00001);
    }

    @Test
    void shouldCalculateSineOfPiDividedByTwo() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(Math.PI / 2);
        SinExpression sinExpression = new SinExpression(innerExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(1.0, result, 0.00001);
    }

    @Test
    void shouldCalculateSineOfNegativeValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(-Math.PI / 2);
        SinExpression sinExpression = new SinExpression(innerExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(-1.0, result, 0.00001);
    }

    @Test
    void shouldCalculateSineOfArbitraryValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(Math.PI / 4);
        SinExpression sinExpression = new SinExpression(innerExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(Math.sqrt(2) / 2, result, 0.00001);
    }

    @Test
    void shouldHandleSineOfLargeValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(12345.6789);
        SinExpression sinExpression = new SinExpression(innerExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(Math.sin(12345.6789), result, 0.00001);
    }
}
