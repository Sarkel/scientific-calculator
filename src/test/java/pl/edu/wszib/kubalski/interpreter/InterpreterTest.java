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

class InterpreterTest {

    @Test
    void interpret_shouldReturnCorrectResult_whenExpressionIsValid() {
        // Arrange
        Context mockContext = mock(Context.class);
        Tokenizer mockTokenizer = mock(Tokenizer.class);
        TokenParserFactory mockTokenParserFactory = mock(TokenParserFactory.class);
        TokenParser mockTokenParser = mock(TokenParser.class);
        Expression mockExpression = mock(Expression.class);

        String expression = "2 + 2";
        List<String> tokens = List.of("2", "+", "2");
        Double expectedResult = 4.0;

        when(mockTokenizer.tokenize(expression)).thenReturn(tokens);
        when(mockTokenParserFactory.create(tokens)).thenReturn(mockTokenParser);
        when(mockTokenParser.parse()).thenReturn(mockExpression);
        when(mockExpression.interpret(mockContext)).thenReturn(expectedResult);

        Interpreter interpreter = new Interpreter(mockContext, mockTokenizer, mockTokenParserFactory);

        // Act
        Double result = interpreter.interpret(expression);

        // Assert
        assertEquals(expectedResult, result);
        verify(mockTokenizer, times(1)).tokenize(expression);
        verify(mockTokenParserFactory, times(1)).create(tokens);
        verify(mockTokenParser, times(1)).parse();
        verify(mockExpression, times(1)).interpret(mockContext);
    }

    @Test
    void interpret_shouldHandleEmptyExpression() {
        // Arrange
        Context mockContext = mock(Context.class);
        Tokenizer mockTokenizer = mock(Tokenizer.class);
        TokenParserFactory mockTokenParserFactory = mock(TokenParserFactory.class);

        String expression = "";
        List<String> tokens = List.of();

        when(mockTokenizer.tokenize(expression)).thenReturn(tokens);
        when(mockTokenParserFactory.create(tokens)).thenThrow(new IllegalArgumentException("Invalid token sequence"));

        Interpreter interpreter = new Interpreter(mockContext, mockTokenizer, mockTokenParserFactory);

        // Act & Assert
        try {
            interpreter.interpret(expression);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid token sequence", e.getMessage());
        }

        verify(mockTokenizer, times(1)).tokenize(expression);
        verify(mockTokenParserFactory, times(1)).create(tokens);
    }

    @Test
    void interpret_shouldThrowException_whenParseFails() {
        // Arrange
        Context mockContext = mock(Context.class);
        Tokenizer mockTokenizer = mock(Tokenizer.class);
        TokenParserFactory mockTokenParserFactory = mock(TokenParserFactory.class);
        TokenParser mockTokenParser = mock(TokenParser.class);

        String expression = "invalid";
        List<String> tokens = List.of("invalid");

        when(mockTokenizer.tokenize(expression)).thenReturn(tokens);
        when(mockTokenParserFactory.create(tokens)).thenReturn(mockTokenParser);
        when(mockTokenParser.parse()).thenThrow(new IllegalArgumentException("Failed to parse tokens"));

        Interpreter interpreter = new Interpreter(mockContext, mockTokenizer, mockTokenParserFactory);

        // Act & Assert
        try {
            interpreter.interpret(expression);
        } catch (IllegalArgumentException e) {
            assertEquals("Failed to parse tokens", e.getMessage());
        }

        verify(mockTokenizer, times(1)).tokenize(expression);
        verify(mockTokenParserFactory, times(1)).create(tokens);
        verify(mockTokenParser, times(1)).parse();
    }
}
