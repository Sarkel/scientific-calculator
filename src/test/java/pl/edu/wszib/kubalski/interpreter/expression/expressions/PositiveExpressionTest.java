package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

/**
 * Test class for PositiveExpression.
 * The PositiveExpression class is responsible for returning the positive value
 * of a given expression by delegating to the interpret method of the wrapped expression.
 */
class PositiveExpressionTest {

    @Test
    void shouldReturnPositiveValue_whenWrappedExpressionReturnsPositiveValue() {
        // Prepare
        Context context = Context.builder().build();
        Expression mockExpression = mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(42.0);

        PositiveExpression positiveExpression = new PositiveExpression(mockExpression);

        // Execute
        Double result = positiveExpression.interpret(context);

        // Verify
        assertEquals(42.0, result);
    }

    @Test
    void shouldReturnNegativeValue_whenWrappedExpressionReturnsNegativeValue() {
        // Prepare
        Context context = Context.builder().build();
        Expression mockExpression = mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(-25.5);

        PositiveExpression positiveExpression = new PositiveExpression(mockExpression);

        // Execute
        Double result = positiveExpression.interpret(context);

        // Verify
        assertEquals(-25.5, result);
    }

    @Test
    void shouldReturnZero_whenWrappedExpressionReturnsZeroValue() {
        // Prepare
        Context context = Context.builder().build();
        Expression mockExpression = mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(0.0);

        PositiveExpression positiveExpression = new PositiveExpression(mockExpression);

        // Execute
        Double result = positiveExpression.interpret(context);

        // Verify
        assertEquals(0.0, result);
    }
}
