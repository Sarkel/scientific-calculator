package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LogExpressionTest {

    /**
     * Tests for the interpret method in the LogExpression class.
     * The LogExpression class is responsible for returning the natural logarithm of the
     * result of the provided expression. It will throw an ArithmeticException for input values
     * that are less than or equal to zero.
     */

    @Test
    void shouldReturnNaturalLogarithm_ForPositiveInput() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(2.718);

        LogExpression logExpression = new LogExpression(mockExpression);

        // Act
        double result = logExpression.interpret(context);

        // Assert
        assertEquals(1, result, 0.001, "The natural logarithm of 2.718 should be approximately 1.");
    }

    @Test
    void shouldThrowArithmeticException_ForNegativeInput() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(-1.0);

        LogExpression logExpression = new LogExpression(mockExpression);

        // Act & Assert
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> logExpression.interpret(context),
                "Logarithm of a negative number should throw an exception."
        );
        assertEquals("Logarithm of a negative number is undefined.", exception.getMessage());
    }

    @Test
    void shouldThrowArithmeticException_ForZeroInput() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(0.0);

        LogExpression logExpression = new LogExpression(mockExpression);

        // Act & Assert
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> logExpression.interpret(context),
                "Logarithm of zero should throw an exception."
        );
        assertEquals("Logarithm of zero is undefined.", exception.getMessage());
    }

    @Test
    void shouldReturnLogarithm_ForInputGreaterThanOne() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(10.0);

        LogExpression logExpression = new LogExpression(mockExpression);

        // Act
        double result = logExpression.interpret(context);

        // Assert
        assertEquals(Math.log(10.0), result, 0.001, "The logarithm of 10 should match the Math.log result.");
    }
}
