package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PositiveExpressionTest {

    /**
     * Class under test: PositiveExpression.
     * <p>
     * Description:
     * The PositiveExpression class represents an interpretation of a numeric
     * expression that always yields the positive value (+) of the result
     * computed by the inner expression.
     * <p>
     * Method under test: interpret(Context context)
     * <p>
     * Description:
     * The interpret method evaluates the given context via the wrapped expression
     * and returns its positive equivalent.
     */

    @Test
    void interpret_shouldReturnPositiveValue_whenExpressionReturnsPositiveValue() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(25.3);

        PositiveExpression positiveExpression = new PositiveExpression(mockedExpression);

        // Act
        Double result = positiveExpression.interpret(context);

        // Assert
        assertEquals(25.3, result);
    }

    @Test
    void interpret_shouldReturnPositiveValue_whenExpressionReturnsNegativeValue() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(-42.7);

        PositiveExpression positiveExpression = new PositiveExpression(mockedExpression);

        // Act
        Double result = positiveExpression.interpret(context);

        // Assert
        assertEquals(-42.7, result);
    }

    @Test
    void interpret_shouldReturnZero_whenExpressionReturnsZero() {
        // Arrange
        Expression mockedExpression = mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(0.0);

        PositiveExpression positiveExpression = new PositiveExpression(mockedExpression);

        // Act
        Double result = positiveExpression.interpret(context);

        // Assert
        assertEquals(0.0, result);
    }
}
