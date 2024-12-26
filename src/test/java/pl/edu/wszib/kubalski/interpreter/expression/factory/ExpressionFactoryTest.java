package pl.edu.wszib.kubalski.interpreter.expression.factory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionType;
import pl.edu.wszib.kubalski.interpreter.expression.expressions.NumberExpression;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpressionFactoryTest {

    /**
     * Unit tests for the createExpression method in the ExpressionFactory class.
     * createExpression is responsible for matching operators with their respective Expression types
     * and correctly creating the appropriate Expression implementation.
     */

    @Test
    void shouldCreateHighPriorityExpressionToAdd() {
        // Arrange
        ExpressionFactoryStore mockStore = mock(ExpressionFactoryStore.class);
        ExpressionFactory factory = new ExpressionFactory(mockStore);
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        ExpressionType expressionType = ExpressionType.ADD;
        Expression mockedExpression = mock(Expression.class);
        Map<ExpressionType, ExpressionFactoryStore.TwoArgumentExpressionFactory> highPriorityExpressions = Map.of(
                expressionType, (l, r) -> mockedExpression
        );
        when(mockStore.getHighPriorityExpressions()).thenReturn(highPriorityExpressions);

        // Act
        Expression result = factory.createExpression("+", left, right);

        // Assert
        assertEquals(mockedExpression, result);
        verify(mockStore).getHighPriorityExpressions();
    }

    @Test
    void shouldCreateUnaryExpression() {
        // Arrange
        ExpressionFactoryStore mockStore = mock(ExpressionFactoryStore.class);
        ExpressionFactory factory = new ExpressionFactory(mockStore);
        Expression left = mock(Expression.class);

        ExpressionType expressionType = ExpressionType.NEGATE;
        Expression mockedExpression = mock(Expression.class);
        Map<ExpressionType, ExpressionFactoryStore.OneArgumentExpressionFactory> unaryExpressions = Map.of(
                expressionType, (l) -> mockedExpression
        );
        when(mockStore.getUnaryExpressions()).thenReturn(unaryExpressions);

        // Act
        Expression result = factory.createExpression("-", left, null);

        // Assert
        assertEquals(mockedExpression, result);
        verify(mockStore).getUnaryExpressions();
    }

    @Test
    void shouldThrowExceptionWhenInvalidOperatorIsPassed() {
        // Arrange
        ExpressionFactoryStore mockStore = mock(ExpressionFactoryStore.class);
        ExpressionFactory factory = new ExpressionFactory(mockStore);

        String invalidOperator = "!";
        Expression left = mock(Expression.class);
        Expression right = mock(Expression.class);

        // Act & Assert
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> factory.createExpression(invalidOperator, left, right)
        );
    }

    @Test
    void shouldThrowExceptionForInvalidNumberOfArguments() {
        // Arrange
        ExpressionFactoryStore mockStore = mock(ExpressionFactoryStore.class);
        ExpressionFactory factory = new ExpressionFactory(mockStore);

        String operator = "+";
        Expression left = mock(Expression.class);

        when(mockStore.getHighPriorityExpressions()).thenReturn(Map.of());
        when(mockStore.getUnaryExpressions()).thenReturn(Map.of());
        when(mockStore.getFunctionalExpressions()).thenReturn(Map.of());
        when(mockStore.getLowPriorityExpressions()).thenReturn(Map.of());
        when(mockStore.getConstantExpressions()).thenReturn(Map.of());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.createExpression(operator, left, null)
        );
        assertEquals("Invalid operator or value: " + operator, exception.getMessage());
    }

    @Test
    void shouldCreateConstantExpression() {
        // Arrange
        ExpressionFactoryStore mockStore = mock(ExpressionFactoryStore.class);
        ExpressionFactory factory = new ExpressionFactory(mockStore);

        ExpressionType expressionType = ExpressionType.PI;
        Expression mockedExpression = mock(Expression.class);

        Map<ExpressionType, ExpressionFactoryStore.NoArgumentExpressionFactory> constantExpressions = Map.of(
                expressionType, () -> mockedExpression
        );
        when(mockStore.getConstantExpressions()).thenReturn(constantExpressions);

        // Act
        Expression result = factory.createExpression("constant", null, null);

        // Assert
        assertEquals(mockedExpression, result);
        verify(mockStore).getConstantExpressions();
    }

    @Test
    void shouldCreateFunctionalExpression() {
        // Arrange
        ExpressionFactoryStore mockStore = mock(ExpressionFactoryStore.class);
        ExpressionFactory factory = new ExpressionFactory(mockStore);
        Expression left = mock(Expression.class);

        ExpressionType expressionType = ExpressionType.SIN;
        Expression mockedExpression = mock(Expression.class);

        Map<ExpressionType, ExpressionFactoryStore.OneArgumentExpressionFactory> functionalExpressions = Map.of(
                expressionType, (l) -> mockedExpression
        );
        when(mockStore.getFunctionalExpressions()).thenReturn(functionalExpressions);

        // Act
        Expression result = factory.createExpression("func", left, null);

        // Assert
        assertEquals(mockedExpression, result);
        verify(mockStore).getFunctionalExpressions();
    }
}
