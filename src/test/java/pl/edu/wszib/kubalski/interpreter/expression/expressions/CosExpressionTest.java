package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosExpressionTest {

    /**
     * Test class for the {@link CosExpression} class, specifically focusing on
     * the {@code interpret(Context context)} method. This method computes the cosine
     * of the interpreted result of the provided inner expression using the given context.
     */

    @Test
    void shouldReturnOne_whenInterpretCalledWithZero() {
        // Arrange
        Expression mockedExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(0.0);

        CosExpression cosExpression = new CosExpression(mockedExpression);

        // Act
        Double result = cosExpression.interpret(context);

        // Assert
        assertEquals(1.0, result, 0.0001, "Cosine of 0 should be 1.0");
    }

    @Test
    void shouldReturnZero_whenInterpretCalledWithPiOverTwo() {
        // Arrange
        Expression mockedExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(Math.PI / 2);

        CosExpression cosExpression = new CosExpression(mockedExpression);

        // Act
        Double result = cosExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.0001, "Cosine of π/2 should be approximately 0.0");
    }

    @Test
    void shouldReturnMinusOne_whenInterpretCalledWithPi() {
        // Arrange
        Expression mockedExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(Math.PI);

        CosExpression cosExpression = new CosExpression(mockedExpression);

        // Act
        Double result = cosExpression.interpret(context);

        // Assert
        assertEquals(-1.0, result, 0.0001, "Cosine of π should be -1.0");
    }

    @Test
    void shouldReturnZero_whenInterpretCalledWithNegativePiOverTwo() {
        // Arrange
        Expression mockedExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        Mockito.when(mockedExpression.interpret(context)).thenReturn(-Math.PI / 2);

        CosExpression cosExpression = new CosExpression(mockedExpression);

        // Act
        Double result = cosExpression.interpret(context);

        // Assert
        assertEquals(0.0, result, 0.0001, "Cosine of -π/2 should be approximately 0.0");
    }

    @Test
    void shouldReturnSqrtTwoOverTwo_whenInterpretCalledWithPiOverFour() {
        // Arrange
        Expression mockedExpression = Mockito.mock(Expression.class);
        Context context = Context.builder().build();
        double angle = Math.PI / 4; // 45 degrees
        Mockito.when(mockedExpression.interpret(context)).thenReturn(angle);

        CosExpression cosExpression = new CosExpression(mockedExpression);

        // Act
        Double result = cosExpression.interpret(context);

        // Assert
        assertEquals(Math.sqrt(2) / 2, result, 0.0001,
                "Cosine of π/4 should be approximately sqrt(2)/2");
    }
}
