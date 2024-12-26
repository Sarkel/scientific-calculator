package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class TokenParserFactoryTest {

    /**
     * The `TokenParserFactory` class is responsible for creating instances of the `TokenParser` class.
     * It utilizes the provided `ExpressionFactoryHelper`, `ExpressionFactory`, and prototype `Class<? extends TokenParser>`
     * to dynamically create new instances of `TokenParser` using reflection.
     */

    @Test
    void shouldCreateTokenParserSuccessfully() {
        // Arrange
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        Class<? extends TokenParser> tokenParserClass = MockTokenParser.class;
        List<String> tokens = List.of("token1", "token2");

        TokenParserFactory factory = new TokenParserFactory(expressionFactoryHelper, expressionFactory, tokenParserClass);

        // Act
        TokenParser tokenParser = factory.create(tokens);

        // Assert
        assertNotNull(tokenParser);
        assertNotNull(((MockTokenParser) tokenParser).getTokens());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenConstructorInvocationFails() {
        // Arrange
        ExpressionFactoryHelper expressionFactoryHelper = mock(ExpressionFactoryHelper.class);
        ExpressionFactory expressionFactory = mock(ExpressionFactory.class);
        Class<? extends TokenParser> failingClass = InvalidTokenParser.class;
        List<String> tokens = List.of("token1");

        TokenParserFactory factory = new TokenParserFactory(expressionFactoryHelper, expressionFactory, failingClass);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> factory.create(tokens));
    }

    /**
     * A mock implementation of TokenParser used for testing the factory.
     */
    @Getter
    static class MockTokenParser extends TokenParser {
        private final List<String> tokens;

        public MockTokenParser(List<String> tokens, ExpressionFactoryHelper expressionFactoryHelper, ExpressionFactory expressionFactory) {
            super(tokens, expressionFactoryHelper, expressionFactory);
            this.tokens = tokens;
        }

        @Override
        public Expression parse() {
            return null;
        }
    }

    /**
     * A mock implementation of TokenParser that lacks the required constructor to simulate a failure case.
     */
    static class InvalidTokenParser extends TokenParser {
        public InvalidTokenParser() {
            super(null, null, null);
        }

        @Override
        public Expression parse() {
            return null;
        }
    }
}
