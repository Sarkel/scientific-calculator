package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TanExpressionTest {

    /**
     * This class tests the `interpret` method of the `TanExpression` class, which evaluates the tangent
     * of the value obtained by interpreting the provided expression within the given context.
     */

    @Test
    void interpret_shouldReturnTangentOfExpressionValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(context)).thenReturn(Math.PI / 4); // π/4 is 45 degrees, tan(π/4) = 1
        TanExpression tanExpression = new TanExpression(mockExpression);

        // Act
        Double result = tanExpression.interpret(context);

        // Assert
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    void interpret_shouldHandleZeroValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(context)).thenReturn(0.0); // tan(0) = 0
        TanExpression tanExpression = new TanExpression(mockExpression);

        // Act
        Double result = tanExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void interpret_shouldHandleNegativeValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(context)).thenReturn(-Math.PI / 4); // tan(-π/4) = -1
        TanExpression tanExpression = new TanExpression(mockExpression);

        // Act
        Double result = tanExpression.interpret(context);

        // Assert
        assertEquals(-1.0, result, 0.0001);
    }

    @Test
    void interpret_shouldHandleNonTrivialValue() {
        // Arrange
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(context)).thenReturn(Math.PI / 3); // tan(π/3) = √3
        TanExpression tanExpression = new TanExpression(mockExpression);

        // Act
        Double result = tanExpression.interpret(context);

        // Assert
        assertEquals(Math.sqrt(3), result, 0.0001);
    }
}
