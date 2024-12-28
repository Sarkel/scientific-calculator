package pl.edu.wszib.kubalski.interpreter.parser;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TokenParserTest {

    @Test
    void shouldParseExpression_whenSimpleAddition() {
        // Arrange
        List<String> tokens = List.of("5", "+", "3");
        ExpressionFactoryHelper factoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory factory = mock(ExpressionFactory.class);

        when(factoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"+"});

        when(factoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getUnaryExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});

        Expression operand1 = mock(Expression.class);
        Expression operand2 = mock(Expression.class);
        Expression result = mock(Expression.class);

        when(factory.createExpression("5", null, null)).thenReturn(operand1);
        when(factory.createExpression("3", null, null)).thenReturn(operand2);
        when(factory.createExpression("+", operand1, operand2)).thenReturn(result);

        TokenParser parser = new TokenParser(tokens, factoryHelper, factory);

        // Act
        Expression output = parser.parse();

        // Assert
        assertEquals(result, output);
        verify(factory).createExpression("+", operand1, operand2);
    }

    @Test
    void shouldParseExpression_whenNestedWithParentheses() {
        // Arrange
        List<String> tokens = List.of("(", "5", "+", "3", ")", "*", "2");
        ExpressionFactoryHelper factoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory factory = mock(ExpressionFactory.class);

        when(factoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"+"});
        when(factoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{"*"});

        when(factoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getUnaryExpressions()).thenReturn(new String[]{});

        Expression innerOperand1 = mock(Expression.class);
        Expression innerOperand2 = mock(Expression.class);
        Expression innerResult = mock(Expression.class);
        Expression outerOperand = mock(Expression.class);
        Expression finalResult = mock(Expression.class);

        when(factory.createExpression("5", null, null)).thenReturn(innerOperand1);
        when(factory.createExpression("3", null, null)).thenReturn(innerOperand2);
        when(factory.createExpression("+", innerOperand1, innerOperand2)).thenReturn(innerResult);
        when(factory.createExpression("2", null, null)).thenReturn(outerOperand);
        when(factory.createExpression("*", innerResult, outerOperand)).thenReturn(finalResult);

        TokenParser parser = new TokenParser(tokens, factoryHelper, factory);

        // Act
        Expression output = parser.parse();

        // Assert
        assertEquals(finalResult, output);
        verify(factory).createExpression("+", innerOperand1, innerOperand2);
        verify(factory).createExpression("*", innerResult, outerOperand);
    }

    @Test
    void shouldParseExpression_whenUnaryOperator() {
        // Arrange
        List<String> tokens = List.of("-", "5");
        ExpressionFactoryHelper factoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory factory = mock(ExpressionFactory.class);

        when(factoryHelper.getUnaryExpressions()).thenReturn(new String[]{"-"});

        when(factoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});

        Expression operand = mock(Expression.class);
        Expression result = mock(Expression.class);

        when(factory.createExpression("5", null, null)).thenReturn(operand);
        when(factory.createExpression("-", operand, null)).thenReturn(result);

        TokenParser parser = new TokenParser(tokens, factoryHelper, factory);

        // Act
        Expression output = parser.parse();

        // Assert
        assertEquals(result, output);
        verify(factory).createExpression("-", operand, null);
    }

    @Test
    void shouldParseExpression_whenFunctionCall() {
        // Arrange
        List<String> tokens = List.of("sin", "(", "3.14", ")");
        ExpressionFactoryHelper factoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory factory = mock(ExpressionFactory.class);

        when(factoryHelper.getFunctionalExpressions()).thenReturn(new String[]{"sin"});
        when(factoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getUnaryExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});

        Expression argument = mock(Expression.class);
        Expression result = mock(Expression.class);

        when(factory.createExpression("3.14", null, null)).thenReturn(argument);
        when(factory.createExpression("sin", argument, null)).thenReturn(result);

        TokenParser parser = new TokenParser(tokens, factoryHelper, factory);

        // Act
        Expression output = parser.parse();

        // Assert
        assertEquals(result, output);
        verify(factory).createExpression("sin", argument, null);
    }

    @Test
    void shouldThrowException_whenUnexpectedToken() {
        // Arrange
        List<String> tokens = List.of("&");
        ExpressionFactoryHelper factoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory factory = mock(ExpressionFactory.class);

        when(factoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getUnaryExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        when(factoryHelper.getConstantExpressions()).thenReturn(new String[]{});

        TokenParser parser = new TokenParser(tokens, factoryHelper, factory);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, parser::parse);
    }
}
