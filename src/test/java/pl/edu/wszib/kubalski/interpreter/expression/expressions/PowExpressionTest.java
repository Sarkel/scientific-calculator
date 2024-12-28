package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowExpressionTest {

    /**
     * Test class for PowExpression, which calculates the result
     * of raising the result of one expression (base) to the power
     * of another expression (exponent) using Math.pow.
     */

    @Test
    void shouldCalculatePowerCorrectly_whenBaseIsPositiveAndExponentIsPositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression base = Mockito.mock(Expression.class);
        Expression exponent = Mockito.mock(Expression.class);
        Mockito.when(base.interpret(context)).thenReturn(2.0);
        Mockito.when(exponent.interpret(context)).thenReturn(3.0);

        PowExpression powExpression = new PowExpression(base, exponent);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(8.0, result);
    }

    @Test
    void shouldReturnZero_whenBaseIsZeroAndExponentIsPositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression base = Mockito.mock(Expression.class);
        Expression exponent = Mockito.mock(Expression.class);
        Mockito.when(base.interpret(context)).thenReturn(0.0);
        Mockito.when(exponent.interpret(context)).thenReturn(3.0);

        PowExpression powExpression = new PowExpression(base, exponent);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void shouldReturnOne_whenBaseIsPositiveAndExponentIsZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression base = Mockito.mock(Expression.class);
        Expression exponent = Mockito.mock(Expression.class);
        Mockito.when(base.interpret(context)).thenReturn(5.0);
        Mockito.when(exponent.interpret(context)).thenReturn(0.0);

        PowExpression powExpression = new PowExpression(base, exponent);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(1.0, result);
    }

    @Test
    void shouldCalculatePowerCorrectly_whenBaseIsNegativeAndExponentIsPositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression base = Mockito.mock(Expression.class);
        Expression exponent = Mockito.mock(Expression.class);
        Mockito.when(base.interpret(context)).thenReturn(-2.0);
        Mockito.when(exponent.interpret(context)).thenReturn(3.0);

        PowExpression powExpression = new PowExpression(base, exponent);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(-8.0, result);
    }

    @Test
    void shouldCalculatePowerCorrectly_whenBaseIsPositiveAndExponentIsNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression base = Mockito.mock(Expression.class);
        Expression exponent = Mockito.mock(Expression.class);
        Mockito.when(base.interpret(context)).thenReturn(2.0);
        Mockito.when(exponent.interpret(context)).thenReturn(-2.0);

        PowExpression powExpression = new PowExpression(base, exponent);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(0.25, result);
    }

    @Test
    void shouldReturnOne_whenBaseIsZeroAndExponentIsZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression base = Mockito.mock(Expression.class);
        Expression exponent = Mockito.mock(Expression.class);
        Mockito.when(base.interpret(context)).thenReturn(0.0);
        Mockito.when(exponent.interpret(context)).thenReturn(0.0);

        PowExpression powExpression = new PowExpression(base, exponent);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(1.0, result);
    }
}
