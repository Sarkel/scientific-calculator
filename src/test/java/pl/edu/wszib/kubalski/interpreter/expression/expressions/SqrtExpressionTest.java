package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SqrtExpressionTest {

    /**
     * SqrtExpression is a class that represents a square root operation applied
     * on a nested expression. It evaluates the nested expression within the provided
     * context and computes the square root of the result. If the nested expression
     * evaluates to a negative value, an ArithmeticException is thrown.
     */

    @Test
    void shouldReturnSquareRoot_whenInputIsPositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression nestedExpression = mock(Expression.class);
        when(nestedExpression.interpret(context)).thenReturn(25.0);

        SqrtExpression sqrtExpression = new SqrtExpression(nestedExpression);

        // Act
        Double result = sqrtExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowArithmeticException_whenInputIsNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression nestedExpression = mock(Expression.class);
        when(nestedExpression.interpret(context)).thenReturn(-1.0);

        SqrtExpression sqrtExpression = new SqrtExpression(nestedExpression);

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> sqrtExpression.interpret(context));
    }

    @Test
    void shouldReturnZero_whenInputIsZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression nestedExpression = mock(Expression.class);
        when(nestedExpression.interpret(context)).thenReturn(0.0);

        SqrtExpression sqrtExpression = new SqrtExpression(nestedExpression);

        // Act
        Double result = sqrtExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void shouldReturnSquareRoot_whenInputIsFractional() {
        // Arrange
        Context context = Context.builder().build();
        Expression nestedExpression = mock(Expression.class);
        when(nestedExpression.interpret(context)).thenReturn(0.25);

        SqrtExpression sqrtExpression = new SqrtExpression(nestedExpression);

        // Act
        Double result = sqrtExpression.interpret(context);

        // Assert
        assertEquals(0.5, result);
    }
}
