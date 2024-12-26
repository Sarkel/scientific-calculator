package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MultiplyExpressionTest {

    /**
     * Test class for the MultiplyExpression class.
     * Focuses on ensuring the interpret method correctly multiplies values
     * from two provided expressions using the given context.
     */

    @Test
    void interpret_shouldReturnCorrectProduct_whenBothOperandsArePositive() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        when(left.interpret(context)).thenReturn(4.0);
        when(right.interpret(context)).thenReturn(3.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(12.0, result);
    }

    @Test
    void interpret_shouldReturnZero_whenOneOperandIsZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        when(left.interpret(context)).thenReturn(0.0);
        when(right.interpret(context)).thenReturn(5.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void interpret_shouldReturnNegativeProduct_whenOneOperandIsNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        when(left.interpret(context)).thenReturn(-4.0);
        when(right.interpret(context)).thenReturn(3.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(-12.0, result);
    }

    @Test
    void interpret_shouldReturnPositiveProduct_whenBothOperandsAreNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        when(left.interpret(context)).thenReturn(-2.0);
        when(right.interpret(context)).thenReturn(-3.0);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(6.0, result);
    }

    @Test
    void interpret_shouldReturnCorrectProduct_whenOperandsAreDecimalNumbers() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        when(left.interpret(context)).thenReturn(2.5);
        when(right.interpret(context)).thenReturn(1.2);

        MultiplyExpression multiplyExpression = new MultiplyExpression(left, right);

        // Act
        Double result = multiplyExpression.interpret(context);

        // Assert
        assertEquals(3.0, result);
    }
}
