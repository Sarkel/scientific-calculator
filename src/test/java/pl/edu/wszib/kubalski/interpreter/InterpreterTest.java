package pl.edu.wszib.kubalski.interpreter;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParser;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParserFactory;
import pl.edu.wszib.kubalski.interpreter.tokenizer.Tokenizer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InterpreterTest {

    /**
     * Class: Interpreter
     * Method: interpret(String expression)
     * <p>
     * Overview: The interpret method takes a mathematical or logical expression as input in String format, tokenizes it,
     * parses the tokens to construct an expression tree, and evaluates the expression within a given context to return a result.
     */

    @Test
    public void shouldInterpretSimpleExpression() {
        Context context = Context.builder().build();
        Tokenizer mockTokenizer = mock(Tokenizer.class);
        TokenParserFactory mockTokenParserFactory = mock(TokenParserFactory.class);
        TokenParser mockTokenParser = mock(TokenParser.class);
        Expression mockExpression = mock(Expression.class);

        String inputExpression = "2 + 3";
        List<String> tokens = List.of("2", "+", "3");
        double expectedResult = 5.0;

        when(mockTokenizer.tokenize(inputExpression)).thenReturn(tokens);
        when(mockTokenParserFactory.create(tokens)).thenReturn(mockTokenParser);
        when(mockTokenParser.parse()).thenReturn(mockExpression);
        when(mockExpression.interpret(context)).thenReturn(expectedResult);

        Interpreter interpreter = new Interpreter(context, mockTokenizer, mockTokenParserFactory);

        double result = interpreter.interpret(inputExpression);

        assertEquals(expectedResult, result, 0.0001);

        verify(mockTokenizer).tokenize(inputExpression);
        verify(mockTokenParserFactory).create(tokens);
        verify(mockTokenParser).parse();
        verify(mockExpression).interpret(context);
    }

    @Test
    public void shouldInterpretComplexExpression() {
        Context context = Context.builder().build();
        Tokenizer mockTokenizer = mock(Tokenizer.class);
        TokenParserFactory mockTokenParserFactory = mock(TokenParserFactory.class);
        TokenParser mockTokenParser = mock(TokenParser.class);
        Expression mockExpression = mock(Expression.class);

        String inputExpression = "5 * (2 + 3)";
        List<String> tokens = List.of("5", "*", "(", "2", "+", "3", ")");
        double expectedResult = 25.0;

        when(mockTokenizer.tokenize(inputExpression)).thenReturn(tokens);
        when(mockTokenParserFactory.create(tokens)).thenReturn(mockTokenParser);
        when(mockTokenParser.parse()).thenReturn(mockExpression);
        when(mockExpression.interpret(context)).thenReturn(expectedResult);

        Interpreter interpreter = new Interpreter(context, mockTokenizer, mockTokenParserFactory);

        double result = interpreter.interpret(inputExpression);

        assertEquals(expectedResult, result, 0.0001);

        verify(mockTokenizer).tokenize(inputExpression);
        verify(mockTokenParserFactory).create(tokens);
        verify(mockTokenParser).parse();
        verify(mockExpression).interpret(context);
    }

    @Test
    public void shouldHandleInvalidExpression() {
        Context context = Context.builder().build();
        Tokenizer mockTokenizer = mock(Tokenizer.class);
        TokenParserFactory mockTokenParserFactory = mock(TokenParserFactory.class);
        TokenParser mockTokenParser = mock(TokenParser.class);

        String inputExpression = "invalid_expression";
        List<String> tokens = List.of("invalid_expression");

        when(mockTokenizer.tokenize(inputExpression)).thenReturn(tokens);
        when(mockTokenParserFactory.create(tokens)).thenReturn(mockTokenParser);
        when(mockTokenParser.parse()).thenThrow(new IllegalArgumentException("Invalid expression"));

        Interpreter interpreter = new Interpreter(context, mockTokenizer, mockTokenParserFactory);

        try {
            interpreter.interpret(inputExpression);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid expression", e.getMessage());
        }

        verify(mockTokenizer).tokenize(inputExpression);
        verify(mockTokenParserFactory).create(tokens);
        verify(mockTokenParser).parse();
    }
}
