package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowExpressionTest {

    /**
     * PowExpression class represents a mathematical power operation between two expressions
     * and interprets them based on a given context.
     * <p>
     * The interpret method calculates the power of the left expression's result raised
     * to the right expression's result within the given context.
     */

    @Test
    void testInterpretWithPositiveExponents() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(2.0);
        Mockito.when(right.interpret(context)).thenReturn(3.0);

        PowExpression powExpression = new PowExpression(left, right);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(8.0, result);
    }

    @Test
    void testInterpretWithNegativeExponent() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(2.0);
        Mockito.when(right.interpret(context)).thenReturn(-2.0);

        PowExpression powExpression = new PowExpression(left, right);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(0.25, result);
    }

    @Test
    void testInterpretWithZeroExponent() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(5.0);
        Mockito.when(right.interpret(context)).thenReturn(0.0);

        PowExpression powExpression = new PowExpression(left, right);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(1.0, result);
    }

    @Test
    void testInterpretWithZeroBaseAndNonZeroExponent() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(5.0);

        PowExpression powExpression = new PowExpression(left, right);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void testInterpretWithZeroBaseAndZeroExponent() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(0.0);

        PowExpression powExpression = new PowExpression(left, right);

        // Act
        Double result = powExpression.interpret(context);

        // Assert
        assertEquals(1.0, result); // By convention, 0^0 is generally treated as 1 in mathematical operations.
    }
}
