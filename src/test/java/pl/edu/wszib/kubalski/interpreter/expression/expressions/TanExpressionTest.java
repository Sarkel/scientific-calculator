package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TanExpressionTest {

    /**
     * Tests the `interpret` method of `TanExpression`.
     * This method evaluates the tangent of the result of another `Expression`
     * in the context provided.
     */

    @Test
    void shouldReturnValidResult_whenInnerExpressionIsValid() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(Math.PI / 4); // tan(π/4) = 1
        TanExpression tanExpression = new TanExpression(innerExpression);

        // Act
        double result = tanExpression.interpret(context);

        // Assert
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    void shouldReturnZero_whenInnerExpressionEvaluatesToZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(0.0); // tan(0) = 0
        TanExpression tanExpression = new TanExpression(innerExpression);

        // Act
        double result = tanExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void shouldThrowArithmeticException_whenInnerExpressionCausesInfiniteResult() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(Double.POSITIVE_INFINITY);
        TanExpression tanExpression = new TanExpression(innerExpression);

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> tanExpression.interpret(context));
    }

    @Test
    void shouldThrowArithmeticException_whenInnerExpressionEvaluatesToNaN() {
        // Arrange
        Context context = Context.builder().build();
        Expression innerExpression = mock(Expression.class);
        when(innerExpression.interpret(context)).thenReturn(Double.NaN); // Input as NaN
        TanExpression tanExpression = new TanExpression(innerExpression);

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> tanExpression.interpret(context));
    }
}
