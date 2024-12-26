package pl.edu.wszib.kubalski.interpreter.parser;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenParserTest {

    @Test
    void shouldParseSimpleConstantExpression() {
        List<String> tokens = List.of("42");
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);

        when(expressionFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(expressionFactory.createExpression("42", null, null)).thenReturn(mock(Expression.class));

        TokenParser tokenParser = new TokenParser(tokens, expressionFactoryHelper, expressionFactory);

        Expression result = tokenParser.parse();

        assertNotNull(result);
        verify(expressionFactory).createExpression("42", null, null);
    }

    @Test
    void shouldParseUnaryExpression() {
        List<String> tokens = List.of("-", "42");
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);

        when(expressionFactoryHelper.getUnaryExpressions()).thenReturn(new String[]{"-"});
        when(expressionFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(expressionFactory.createExpression("-", mock(Expression.class), null)).thenReturn(mock(Expression.class));
        when(expressionFactory.createExpression("42", null, null)).thenReturn(mock(Expression.class));

        TokenParser tokenParser = new TokenParser(tokens, expressionFactoryHelper, expressionFactory);

        Expression result = tokenParser.parse();

        assertNotNull(result);
        verify(expressionFactory).createExpression("-", any(Expression.class), eq(null));
    }

    @Test
    void shouldParseSimpleAdditionExpression() {
        List<String> tokens = List.of("42", "+", "58");
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);

        when(expressionFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"+"});
        when(expressionFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(expressionFactory.createExpression("42", null, null)).thenReturn(mock(Expression.class));
        when(expressionFactory.createExpression("58", null, null)).thenReturn(mock(Expression.class));
        when(expressionFactory.createExpression("+", mock(Expression.class), mock(Expression.class)))
                .thenReturn(mock(Expression.class));

        TokenParser tokenParser = new TokenParser(tokens, expressionFactoryHelper, expressionFactory);

        Expression result = tokenParser.parse();

        assertNotNull(result);
        verify(expressionFactory).createExpression("+", any(Expression.class), any(Expression.class));
    }

    @Test
    void shouldParseParenthesizedExpression() {
        List<String> tokens = List.of("(", "42", "+", "58", ")");
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);

        when(expressionFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"+"});
        when(expressionFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(expressionFactory.createExpression("42", null, null)).thenReturn(mock(Expression.class));
        when(expressionFactory.createExpression("58", null, null)).thenReturn(mock(Expression.class));
        when(expressionFactory.createExpression("+", mock(Expression.class), mock(Expression.class)))
                .thenReturn(mock(Expression.class));

        TokenParser tokenParser = new TokenParser(tokens, expressionFactoryHelper, expressionFactory);

        Expression result = tokenParser.parse();

        assertNotNull(result);
        verify(expressionFactory).createExpression("+", any(Expression.class), any(Expression.class));
    }

    @Test
    void shouldParseFunctionExpression() {
        List<String> tokens = List.of("sqrt", "(", "25", ")");
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);

        when(expressionFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{"sqrt"});
        when(expressionFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});
        when(expressionFactory.createExpression("25", null, null)).thenReturn(mock(Expression.class));
        when(expressionFactory.createExpression("sqrt", mock(Expression.class), null))
                .thenReturn(mock(Expression.class));

        TokenParser tokenParser = new TokenParser(tokens, expressionFactoryHelper, expressionFactory);

        Expression result = tokenParser.parse();

        assertNotNull(result);
        verify(expressionFactory).createExpression("sqrt", any(Expression.class), eq(null));
    }

    @Test
    void shouldThrowWhenUnexpectedToken() {
        List<String> tokens = List.of("&");
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);

        TokenParser tokenParser = new TokenParser(tokens, expressionFactoryHelper, expressionFactory);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, tokenParser::parse);
        assertEquals("Unexpected token: &", exception.getMessage());
    }
}
