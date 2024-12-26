package pl.edu.wszib.kubalski.interpreter.expression.factory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionType;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ExpressionFactoryHelperTest {

    /**
     * Unit tests for the ExpressionFactoryHelper class.
     * The class ExpressionFactoryHelper provides utility methods to retrieve different types
     * of expressions from the ExpressionFactoryStore using their respective keys.
     */

    @Test
    void testGetFunctionalExpressions_returnsCorrectOperators() {
        // Arrange
        ExpressionType funcExpr1 = Mockito.mock(ExpressionType.class);
        ExpressionType funcExpr2 = Mockito.mock(ExpressionType.class);
        Mockito.when(funcExpr1.getOperator()).thenReturn("FUNC1");
        Mockito.when(funcExpr2.getOperator()).thenReturn("FUNC2");

        ExpressionFactoryStore mockStore = Mockito.mock(ExpressionFactoryStore.class);
        Mockito.when(mockStore.getFunctionalExpressions()).thenReturn(Map.of(funcExpr1, null, funcExpr2, null));

        ExpressionFactoryHelper helper = new ExpressionFactoryHelper(mockStore);

        // Act
        String[] result = helper.getFunctionalExpressions();

        // Assert
        assertArrayEquals(new String[]{"FUNC1", "FUNC2"}, result);
    }

    @Test
    void testGetFunctionalExpressions_returnsEmptyArrayWhenStoreEmpty() {
        // Arrange
        ExpressionFactoryStore mockStore = Mockito.mock(ExpressionFactoryStore.class);
        Mockito.when(mockStore.getFunctionalExpressions()).thenReturn(Collections.emptyMap());

        ExpressionFactoryHelper helper = new ExpressionFactoryHelper(mockStore);

        // Act
        String[] result = helper.getFunctionalExpressions();

        // Assert
        assertArrayEquals(new String[]{}, result);
    }

    @Test
    void testGetFunctionalExpressions_handlesSingleExpression() {
        // Arrange
        ExpressionType funcExpr = Mockito.mock(ExpressionType.class);
        Mockito.when(funcExpr.getOperator()).thenReturn("FUNC");

        ExpressionFactoryStore mockStore = Mockito.mock(ExpressionFactoryStore.class);
        Mockito.when(mockStore.getFunctionalExpressions()).thenReturn(Map.of(funcExpr, null));

        ExpressionFactoryHelper helper = new ExpressionFactoryHelper(mockStore);

        // Act
        String[] result = helper.getFunctionalExpressions();

        // Assert
        assertArrayEquals(new String[]{"FUNC"}, result);
    }
}
