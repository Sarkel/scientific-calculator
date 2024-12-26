package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosExpressionTest {

    /**
     * This class tests the CosExpression class, specifically its interpret method.
     * The interpret method calculates the cosine of the value returned by the inner expression's interpret method.
     */

    @Test
    void interpret_shouldReturnCosineOfInnerExpressionResult() {
        // Arrange
        Expression innerExpression = Mockito.mock(Expression.class);
        Context dummyContext = Context.builder().build();
        Mockito.when(innerExpression.interpret(dummyContext)).thenReturn(Math.PI / 3);

        CosExpression cosExpression = new CosExpression(innerExpression);

        // Act
        Double result = cosExpression.interpret(dummyContext);

        // Assert
        assertEquals(0.5, result, 0.00001);
    }

    @Test
    void interpret_shouldHandleZeroInput() {
        // Arrange
        Expression innerExpression = Mockito.mock(Expression.class);
        Context dummyContext = Context.builder().build();
        Mockito.when(innerExpression.interpret(dummyContext)).thenReturn(0.0);

        CosExpression cosExpression = new CosExpression(innerExpression);

        // Act
        Double result = cosExpression.interpret(dummyContext);

        // Assert
        assertEquals(1.0, result, 0.00001);
    }

    @Test
    void interpret_shouldReturnNegativeCosineForInputBeyondPi() {
        // Arrange
        Expression innerExpression = Mockito.mock(Expression.class);
        Context dummyContext = Context.builder().build();
        Mockito.when(innerExpression.interpret(dummyContext)).thenReturn(3 * Math.PI / 2);

        CosExpression cosExpression = new CosExpression(innerExpression);

        // Act
        Double result = cosExpression.interpret(dummyContext);

        // Assert
        assertEquals(0.0, result, 0.00001);
    }

    @Test
    void interpret_shouldHandleNegativeInputValues() {
        // Arrange
        Expression innerExpression = Mockito.mock(Expression.class);
        Context dummyContext = Context.builder().build();
        Mockito.when(innerExpression.interpret(dummyContext)).thenReturn(-Math.PI / 2);

        CosExpression cosExpression = new CosExpression(innerExpression);

        // Act
        Double result = cosExpression.interpret(dummyContext);

        // Assert
        assertEquals(0.0, result, 0.00001);
    }
}
