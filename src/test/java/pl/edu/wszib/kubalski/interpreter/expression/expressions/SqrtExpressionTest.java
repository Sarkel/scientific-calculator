package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SqrtExpressionTest {

    /**
     * Class under test: SqrtExpression
     * <p>
     * Description: SqrtExpression is an implementation of the Expression interface.
     * It evaluates the square root of the value produced by another Expression.
     * It uses the Math.sqrt() function for computation.
     */

    @Test
    void interpret_shouldReturnCorrectSquareRoot_whenExpressionEvaluatesToPositiveNumber() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(16.0);

        SqrtExpression sqrtExpression = new SqrtExpression(mockedExpression);

        // Act
        Double result = sqrtExpression.interpret(context);

        // Assert
        assertEquals(4.0, result, 0.00001);
    }

    @Test
    void interpret_shouldReturnZero_whenExpressionEvaluatesToZero() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(0.0);

        SqrtExpression sqrtExpression = new SqrtExpression(mockedExpression);

        // Act
        Double result = sqrtExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.00001);
    }

    @Test
    void interpret_shouldThrowException_whenExpressionEvaluatesToNegativeNumber() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        when(mockedExpression.interpret(context)).thenReturn(-4.0);

        SqrtExpression sqrtExpression = new SqrtExpression(mockedExpression);

        // Act & Assert
        assertThrows(ArithmeticException.class, () ->
                sqrtExpression.interpret(context)
        );
    }
}
