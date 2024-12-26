package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogExpressionTest {

    /**
     * This class tests the `interpret` method of the `LogExpression` class.
     * The `LogExpression` class calculates the natural logarithm of the result
     * of another `Expression`, evaluated in the specified `Context`.
     */

    @Test
    void testInterpretWithPositiveValue() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(10.0);

        LogExpression logExpression = new LogExpression(mockedExpression);

        // Act
        Double result = logExpression.interpret(context);

        // Assert
        assertEquals(Math.log(10.0), result, 1e-9);
    }

    @Test
    void testInterpretWithOneAsInput() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(1.0);

        LogExpression logExpression = new LogExpression(mockedExpression);

        // Act
        Double result = logExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testInterpretWithSmallPositiveValue() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(0.5);

        LogExpression logExpression = new LogExpression(mockedExpression);

        // Act
        Double result = logExpression.interpret(context);

        // Assert
        assertEquals(Math.log(0.5), result, 1e-9);
    }

    @Test
    void testInterpretWithLargePositiveValue() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(1000.0);

        LogExpression logExpression = new LogExpression(mockedExpression);

        // Act
        Double result = logExpression.interpret(context);

        // Assert
        assertEquals(Math.log(1000.0), result, 1e-9);
    }

    @Test
    void testInterpretThrowsExceptionForNonPositiveInput() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(-1.0);

        LogExpression logExpression = new LogExpression(mockedExpression);

        // Act & Assert
        try {
            logExpression.interpret(context);
        } catch (ArithmeticException | IllegalArgumentException e) {
            assertEquals("Non-positive value passed to logarithm", e.getMessage());
        }
    }
}
