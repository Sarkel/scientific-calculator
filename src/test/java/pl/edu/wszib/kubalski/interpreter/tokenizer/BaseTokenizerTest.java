package pl.edu.wszib.kubalski.interpreter.tokenizer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseTokenizerTest {

    /**
     * Test class for BaseTokenizer.
     * Verifies the functionality of tokenize method, which splits expressions
     * into a list of tokens based on operators, numbers, functions, and constants.
     */

    @Test
    void shouldTokenize_whenExpressionContainsNumbersOnly() {
        // Arrange
        ExpressionFactoryHelper mockFactoryHelper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(mockFactoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(mockFactoryHelper);
        String expression = "123 456.78";

        // Act
        List<String> tokens = tokenizer.tokenize(expression);

        // Assert
        assertEquals(List.of("123", "456.78"), tokens);
    }

    @Test
    void shouldTokenize_whenExpressionContainsOperators() {
        // Arrange
        ExpressionFactoryHelper mockFactoryHelper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(mockFactoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(mockFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"*", "/"});
        Mockito.when(mockFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(mockFactoryHelper);
        String expression = "2+3-4*5/6";

        // Act
        List<String> tokens = tokenizer.tokenize(expression);

        // Assert
        assertEquals(List.of("2", "+", "3", "-", "4", "*", "5", "/", "6"), tokens);
    }

    @Test
    void shouldTokenize_whenExpressionContainsFunctionsAndConstants() {
        // Arrange
        ExpressionFactoryHelper mockFactoryHelper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(mockFactoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"+"});
        Mockito.when(mockFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{"sin", "cos"});
        Mockito.when(mockFactoryHelper.getConstantExpressions()).thenReturn(new String[]{"pi", "e"});

        BaseTokenizer tokenizer = new BaseTokenizer(mockFactoryHelper);
        String expression = "sin(pi)+cos(e)";

        // Act
        List<String> tokens = tokenizer.tokenize(expression);

        // Assert
        assertEquals(List.of("sin", "(", "pi", ")", "+", "cos", "(", "e", ")"), tokens);
    }

    @Test
    void shouldTokenize_whenExpressionIsEmpty() {
        // Arrange
        ExpressionFactoryHelper mockFactoryHelper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(mockFactoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(mockFactoryHelper);
        String expression = "";

        // Act
        List<String> tokens = tokenizer.tokenize(expression);

        // Assert
        assertEquals(List.of(), tokens);
    }

    @Test
    void shouldTokenize_whenExpressionContainsWhitespaceOnly() {
        // Arrange
        ExpressionFactoryHelper mockFactoryHelper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(mockFactoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(mockFactoryHelper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(mockFactoryHelper);
        String expression = "   ";

        // Act
        List<String> tokens = tokenizer.tokenize(expression);

        // Assert
        assertEquals(List.of(), tokens);
    }

    @Test
    void shouldTokenize_whenExpressionContainsMixedElements() {
        // Arrange
        ExpressionFactoryHelper mockFactoryHelper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(mockFactoryHelper.getHighPriorityExpressions()).thenReturn(new String[]{"*", "/"});
        Mockito.when(mockFactoryHelper.getLowPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(mockFactoryHelper.getFunctionalExpressions()).thenReturn(new String[]{"sqrt"});
        Mockito.when(mockFactoryHelper.getConstantExpressions()).thenReturn(new String[]{"pi"});

        BaseTokenizer tokenizer = new BaseTokenizer(mockFactoryHelper);
        String expression = "sqrt(pi)+2*3-4/5";

        // Act
        List<String> tokens = tokenizer.tokenize(expression);

        // Assert
        assertEquals(List.of("sqrt", "(", "pi", ")", "+", "2", "*", "3", "-", "4", "/", "5"), tokens);
    }
}
