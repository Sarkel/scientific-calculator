package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class SinExpressionTest {

    /**
     * Test class for the {@link SinExpression} class.
     * Verifies the behavior of the interpret method,
     * which calculates the sine of the value returned by the inner expression.
     */

    @Test
    void shouldReturnCorrectSinValue_whenExpressionEvaluatesToPiOverTwo() {
        // Arrange
        Expression mockExpression = mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockExpression.interpret(context)).thenReturn(Math.PI / 2);

        SinExpression sinExpression = new SinExpression(mockExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    void shouldReturnZero_whenExpressionEvaluatesToZero() {
        // Arrange
        Expression mockExpression = mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockExpression.interpret(context)).thenReturn(0.0);

        SinExpression sinExpression = new SinExpression(mockExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void shouldReturnNegativeOne_whenExpressionEvaluatesToNegativePiOverTwo() {
        // Arrange
        Expression mockExpression = mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockExpression.interpret(context)).thenReturn(-Math.PI / 2);

        SinExpression sinExpression = new SinExpression(mockExpression);

        // Act
        Double result = sinExpression.interpret(context);

        // Assert
        assertEquals(-1.0, result, 0.0001);
    }
}
