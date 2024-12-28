package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberExpressionTest {

    /**
     * This test class focuses on the `interpret` method of the `NumberExpression` class.
     * The `interpret` method takes a `Context` object as input and parses the `value`
     * field (provided during the instantiation of `NumberExpression`) into a `Double`.
     */

    @Test
    void shouldInterpret_whenInputIsValidDecimalNumber() {
        // Arrange
        String input = "42.5";
        NumberExpression numberExpression = new NumberExpression(input);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(42.5, result);
    }

    @Test
    void shouldInterpret_whenInputIsValidIntegerNumber() {
        // Arrange
        String input = "100";
        NumberExpression numberExpression = new NumberExpression(input);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(100.0, result);
    }

    @Test
    void shouldThrowException_whenInputIsInvalidNumber() {
        // Arrange
        String input = "not-a-number";
        NumberExpression numberExpression = new NumberExpression(input);
        Context context = Context.builder().build();

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> numberExpression.interpret(context));
    }

    @Test
    void shouldThrowException_whenInputIsNull() {
        // Assert
        assertThrows(NullPointerException.class, () -> new NumberExpression(null));
    }

    @Test
    void shouldInterpret_whenInputIsNegativeNumber() {
        // Arrange
        String input = "-123.45";
        NumberExpression numberExpression = new NumberExpression(input);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(-123.45, result);
    }
}
