package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DivideExpressionTest {

    /**
     * Tests the DivideExpression class, specifically its interpret method.
     * The interpret method performs division of two interpreted values
     * from the left and right expressions.
     */

    @Test
    void shouldReturnCorrectResult_whenInputsAreNonZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(10.0);
        when(right.interpret(context)).thenReturn(2.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act
        Double result = divideExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowArithmeticException_whenDivisionByZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(10.0);
        when(right.interpret(context)).thenReturn(0.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act & Assert
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> divideExpression.interpret(context));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void shouldEvaluateLeftAndRightExpressionsIndependently_whenInterpreted() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);
        when(left.interpret(context)).thenReturn(9.0);
        when(right.interpret(context)).thenReturn(3.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act
        Double result = divideExpression.interpret(context);

        // Assert
        assertEquals(3.0, result);
    }
}
