package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NegationExpressionTest {

    /**
     * The NegationExpression class is responsible for negating the result of the given expression.
     * It takes an Expression instance as input and returns the negated value of the result of the interpretation
     * of that expression.
     * <p>
     * interpret method description:
     * - Negates the value returned by the `interpret` method of the wrapped `Expression`.
     * - Requires a `Context` instance to be passed for evaluation.
     */

    @Test
    void testInterpret_WithPositiveResult() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(5.0);

        NegationExpression negationExpression = new NegationExpression(mockExpression);

        // Act
        Double result = negationExpression.interpret(context);

        // Assert
        assertEquals(-5.0, result);
    }

    @Test
    void testInterpret_WithNegativeResult() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(-8.0);

        NegationExpression negationExpression = new NegationExpression(mockExpression);

        // Act
        Double result = negationExpression.interpret(context);

        // Assert
        assertEquals(8.0, result);
    }

    @Test
    void testInterpret_WithZeroResult() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = mock(Expression.class);
        when(mockExpression.interpret(context)).thenReturn(0.0);

        NegationExpression negationExpression = new NegationExpression(mockExpression);

        // Act
        Double result = negationExpression.interpret(context);

        // Assert
        assertEquals(-0.0, result);
    }
}
