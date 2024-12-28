package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddExpressionTest {

    /**
     * The AddExpression class represents an addition operation between two expressions.
     * The interpret method takes a Context object, evaluates the left and right expressions,
     * adds their results, and returns the sum.
     */

    @Test
    void shouldReturnSumOfPositiveNumbers_whenPositiveNumbersAreInterpreted() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(10.0);
        Mockito.when(right.interpret(context)).thenReturn(15.0);
        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(25.0, result);
    }

    @Test
    void shouldReturnSumOfNegativeNumbers_whenNegativeNumbersAreInterpreted() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(-10.0);
        Mockito.when(right.interpret(context)).thenReturn(-25.0);
        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(-35.0, result);
    }

    @Test
    void shouldReturnCorrectSum_whenPositiveAndNegativeNumberAreInterpreted() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(20.0);
        Mockito.when(right.interpret(context)).thenReturn(-5.0);
        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(15.0, result);
    }

    @Test
    void shouldReturnNumber_whenZeroAndNumberAreInterpreted() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(42.0);
        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(42.0, result);
    }

    @Test
    void shouldReturnZero_whenZeroAndZeroAreInterpreted() {
        // Arrange
        Context context = Context.builder().build();
        Expression left = Mockito.mock(Expression.class);
        Expression right = Mockito.mock(Expression.class);

        Mockito.when(left.interpret(context)).thenReturn(0.0);
        Mockito.when(right.interpret(context)).thenReturn(0.0);
        AddExpression addExpression = new AddExpression(left, right);

        // Act
        Double result = addExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }
}
