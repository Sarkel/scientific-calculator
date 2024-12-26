package pl.edu.wszib.kubalski.interpreter.tokenizer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseTokenizerTest {

    /**
     * Tests for the `BaseTokenizer` class.
     * <p>
     * The `BaseTokenizer` class is responsible for tokenizing mathematical or logical expressions.
     * The `tokenize` method processes a given expression and splits it into individual tokens
     * based on predefined patterns, such as numbers, operators, functions, etc.
     */

    @Test
    void testTokenizeWithNumbersAndOperators() {
        ExpressionFactoryHelper helper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(helper.getHighPriorityExpressions()).thenReturn(new String[]{"*", "/"});
        Mockito.when(helper.getLowPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(helper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(helper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(helper);
        String expression = "3+5*2";

        List<String> result = tokenizer.tokenize(expression);

        assertEquals(List.of("3", "+", "5", "*", "2"), result);
    }

    @Test
    void testTokenizeWithParentheses() {
        ExpressionFactoryHelper helper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(helper.getHighPriorityExpressions()).thenReturn(new String[]{"*", "/"});
        Mockito.when(helper.getLowPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(helper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(helper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(helper);
        String expression = "(3+5)*2";

        List<String> result = tokenizer.tokenize(expression);

        assertEquals(List.of("(", "3", "+", "5", ")", "*", "2"), result);
    }

    @Test
    void testTokenizeWithFunctionsAndConstants() {
        ExpressionFactoryHelper helper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(helper.getHighPriorityExpressions()).thenReturn(new String[]{"^"});
        Mockito.when(helper.getLowPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(helper.getFunctionalExpressions()).thenReturn(new String[]{"sin", "cos"});
        Mockito.when(helper.getConstantExpressions()).thenReturn(new String[]{"pi", "e"});

        BaseTokenizer tokenizer = new BaseTokenizer(helper);
        String expression = "sin(PI)+cos(E)";

        List<String> result = tokenizer.tokenize(expression);

        assertEquals(List.of("sin", "(", "pi", ")", "+", "cos", "(", "e", ")"), result);
    }

    @Test
    void testTokenizeWithDecimalNumbers() {
        ExpressionFactoryHelper helper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(helper.getHighPriorityExpressions()).thenReturn(new String[]{"*", "/"});
        Mockito.when(helper.getLowPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(helper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(helper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(helper);
        String expression = "3.14+2.71";

        List<String> result = tokenizer.tokenize(expression);

        assertEquals(List.of("3.14", "+", "2.71"), result);
    }

    @Test
    void testTokenizeWithEmptyExpression() {
        ExpressionFactoryHelper helper = Mockito.mock(ExpressionFactoryHelper.class);
        Mockito.when(helper.getHighPriorityExpressions()).thenReturn(new String[]{"*", "/"});
        Mockito.when(helper.getLowPriorityExpressions()).thenReturn(new String[]{"+", "-"});
        Mockito.when(helper.getFunctionalExpressions()).thenReturn(new String[]{});
        Mockito.when(helper.getConstantExpressions()).thenReturn(new String[]{});

        BaseTokenizer tokenizer = new BaseTokenizer(helper);
        String expression = "";

        List<String> result = tokenizer.tokenize(expression);

        assertEquals(List.of(), result);
    }
}
