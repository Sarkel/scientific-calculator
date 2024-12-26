package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivideExpressionTest {

    /**
     * Test for the `interpret` method in DivideExpression class.
     * This method evaluates the division of two expressions in the given context.
     */

    @Test
    void testInterpret_WhenDivisionIsValid() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(2.0);
        Mockito.when(right.interpret(context)).thenReturn(10.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act
        Double result = divideExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void testInterpret_WhenDivisionByZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(10.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> divideExpression.interpret(context));
    }

    @Test
    void testInterpret_WhenBothOperandsAreNegative() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(-2.0);
        Mockito.when(right.interpret(context)).thenReturn(-10.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act
        Double result = divideExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void testInterpret_WhenLeftOperandIsZero() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(5.0);
        Mockito.when(right.interpret(context)).thenReturn(0.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act
        Double result = divideExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }

    @Test
    void testInterpret_WhenDivisionResultsInDecimalValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(3.0);
        Mockito.when(right.interpret(context)).thenReturn(10.0);

        DivideExpression divideExpression = new DivideExpression(left, right);

        // Act
        Double result = divideExpression.interpret(context);

        // Assert
        assertEquals(3.3333333333333335, result);
    }
}
