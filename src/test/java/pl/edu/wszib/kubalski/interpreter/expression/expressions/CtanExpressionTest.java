package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CtanExpressionTest {

    /**
     * This test class focuses on the CtanExpression class, specifically testing
     * the interpret method, which computes the cotangent (1 / tan) of the value
     * returned by the provided expression. It ensures that division by zero
     * and other edge cases are handled correctly.
     */

    @Test
    void interpret_ShouldReturnValidResultWhenTanIsNonZero() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(Mockito.any(Context.class))).thenReturn(1.0); // tan result is 1
        Context context = Context.builder().build();
        CtanExpression ctanExpression = new CtanExpression(mockExpression);

        // Act
        Double result = ctanExpression.interpret(context);

        // Assert
        assertEquals(1.0, result, 0.0001); // 1 / 1 = 1
    }

    @Test
    void interpret_ShouldThrowArithmeticExceptionWhenTanIsZero() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(Mockito.any(Context.class))).thenReturn(0.0); // tan result is 0
        Context context = Context.builder().build();
        CtanExpression ctanExpression = new CtanExpression(mockExpression);

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> ctanExpression.interpret(context), "Division by zero");
    }

    @Test
    void interpret_ShouldReturnValidNegativeResultWhenTanIsNonZeroNegative() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(Mockito.any(Context.class))).thenReturn(-2.0); // tan result is -2
        Context context = Context.builder().build();
        CtanExpression ctanExpression = new CtanExpression(mockExpression);

        // Act
        Double result = ctanExpression.interpret(context);

        // Assert
        assertEquals(-0.5, result, 0.0001); // 1 / -2 = -0.5
    }

    @Test
    void interpret_ShouldReturnValidResultForFractionalTan() {
        // Arrange
        Expression mockExpression = Mockito.mock(Expression.class);
        Mockito.when(mockExpression.interpret(Mockito.any(Context.class))).thenReturn(0.5); // tan result is 0.5
        Context context = Context.builder().build();
        CtanExpression ctanExpression = new CtanExpression(mockExpression);

        // Act
        Double result = ctanExpression.interpret(context);

        // Assert
        assertEquals(2.0, result, 0.0001); // 1 / 0.5 = 2
    }
}
