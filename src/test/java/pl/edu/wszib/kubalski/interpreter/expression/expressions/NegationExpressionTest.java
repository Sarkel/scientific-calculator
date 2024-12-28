package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NegationExpressionTest {

    /**
     * Test class for NegationExpression.
     * The NegationExpression class negates the numerical result of another Expression’s `interpret` method.
     */

    @Test
    void shouldNegateValue_whenPositiveValueGiven() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockExpression.interpret(context)).thenReturn(10.0);
        NegationExpression negationExpression = new NegationExpression(mockExpression);

        // Act
        Double result = negationExpression.interpret(context);

        // Assert
        assertEquals(-10.0, result);
    }

    @Test
    void shouldNegateValue_whenNegativeValueGiven() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockExpression.interpret(context)).thenReturn(-5.0);
        NegationExpression negationExpression = new NegationExpression(mockExpression);

        // Act
        Double result = negationExpression.interpret(context);

        // Assert
        assertEquals(5.0, result);
    }

    @Test
    void shouldNegateValue_whenZeroGiven() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockExpression.interpret(context)).thenReturn(0.0);
        NegationExpression negationExpression = new NegationExpression(mockExpression);

        // Act
        Double result = negationExpression.interpret(context);

        // Assert
        assertEquals(-0.0, result);
    }
}
