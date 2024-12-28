package pl.edu.wszib.kubalski.interpreter.expression.factory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionType;
import pl.edu.wszib.kubalski.interpreter.expression.expressions.NumberExpression;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpressionFactoryTest {

    /**
     * Tests for the createExpression method in the ExpressionFactory class.
     * <p>
     * The createExpression method is responsible for creating appropriate instances
     * of the Expression class based on the provided operator, and the left/right expressions.
     */

    @Test
    void shouldCreateHighPriorityExpression_whenOperatorMatches() {
        // Arrange
        ExpressionFactoryStore storeMock = mock(ExpressionFactoryStore.class);
        ExpressionType mockExpressionType = mock(ExpressionType.class);
        Expression leftExpression = mock(Expression.class);
        Expression rightExpression = mock(Expression.class);
        Expression expectedExpression = mock(Expression.class);

        String operator = "*";

        when(mockExpressionType.getOperator()).thenReturn(operator);
        when(mockExpressionType.getNumberOfArguments()).thenReturn(2);
        when(storeMock.getHighPriorityExpressions())
                .thenReturn(Map.of(ExpressionType.MULTIPLY, (l, r) -> expectedExpression));

        ExpressionFactory factory = new ExpressionFactory(storeMock);

        // Act
        Expression result = factory.createExpression(operator, leftExpression, rightExpression);

        // Assert
        assertEquals(expectedExpression, result);
    }

    @Test
    void shouldCreateLowPriorityExpression_whenOperatorMatches() {
        // Arrange
        ExpressionFactoryStore storeMock = mock(ExpressionFactoryStore.class);
        ExpressionType mockExpressionType = mock(ExpressionType.class);
        Expression leftExpression = mock(Expression.class);
        Expression rightExpression = mock(Expression.class);
        Expression expectedExpression = mock(Expression.class);

        String operator = "+";

        when(mockExpressionType.getOperator()).thenReturn(operator);
        when(mockExpressionType.getNumberOfArguments()).thenReturn(2);
        when(storeMock.getLowPriorityExpressions())
                .thenReturn(Map.of(ExpressionType.ADD, (l, r) -> expectedExpression));

        ExpressionFactory factory = new ExpressionFactory(storeMock);

        // Act
        Expression result = factory.createExpression(operator, leftExpression, rightExpression);

        // Assert
        assertEquals(expectedExpression, result);
    }

    @Test
    void shouldCreateUnaryExpression_whenOperatorMatches() {
        // Arrange
        ExpressionFactoryStore storeMock = mock(ExpressionFactoryStore.class);
        ExpressionType mockExpressionType = mock(ExpressionType.class);
        Expression leftExpression = mock(Expression.class);
        Expression expectedExpression = mock(Expression.class);

        String operator = "-";

        when(mockExpressionType.getOperator()).thenReturn(operator);
        when(mockExpressionType.getNumberOfArguments()).thenReturn(1);
        when(storeMock.getUnaryExpressions())
                .thenReturn(Map.of(ExpressionType.NEGATE, (l) -> expectedExpression));

        ExpressionFactory factory = new ExpressionFactory(storeMock);

        // Act
        Expression result = factory.createExpression(operator, leftExpression, null);

        // Assert
        assertEquals(expectedExpression, result);
    }

    @Test
    void shouldCreateConstantExpression_whenOperatorMatches() {
        // Arrange
        ExpressionFactoryStore storeMock = mock(ExpressionFactoryStore.class);
        ExpressionType mockExpressionType = mock(ExpressionType.class);
        Expression expectedExpression = mock(Expression.class);

        Context context = Context.builder().build();

        String operator = "pi";

        when(mockExpressionType.getOperator()).thenReturn(operator);
        when(mockExpressionType.getNumberOfArguments()).thenReturn(0);
        when(expectedExpression.interpret(context)).thenReturn(Math.PI);
        when(storeMock.getConstantExpressions())
                .thenReturn(Map.of(ExpressionType.PI, () -> expectedExpression));

        ExpressionFactory factory = new ExpressionFactory(storeMock);

        // Act
        Expression result = factory.createExpression(operator, null, null);
        Double resultDouble = result.interpret(context);

        // Assert
        assertEquals(Math.PI, resultDouble);
    }

    @Test
    void shouldReturnNumberExpression_whenInvalidOperatorProvided() {
        // Arrange
        ExpressionFactoryStore storeMock = mock(ExpressionFactoryStore.class);
        Context contextMock = Context.builder().build();

        ExpressionFactory factory = new ExpressionFactory(storeMock);

        // Act
        Expression result = factory.createExpression("INVALID", null, null);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> result.interpret(contextMock));
        assertInstanceOf(NumberExpression.class, result);
    }
}
