package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import pl.edu.wszib.kubalski.interpreter.Context;

class NumberExpressionTest {

    /**
     * The {@code NumberExpression} class is used to interpret a numeric string
     * and convert it into a {@code Double} type.
     * <p>
     * The {@code interpret} method parses the numeric string value provided
     * during object creation and returns it as a {@code Double}.
     */

    @Test
    void interpret_validIntegerInput_returnsCorrectDouble() {
        // Arrange
        String value = "42";
        NumberExpression numberExpression = new NumberExpression(value);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(42.0, result);
    }

    @Test
    void interpret_validDecimalInput_returnsCorrectDouble() {
        // Arrange
        String value = "3.14";
        NumberExpression numberExpression = new NumberExpression(value);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(3.14, result);
    }

    @Test
    void interpret_largeValidNumberInput_returnsCorrectDouble() {
        // Arrange
        String value = "123456789.12345";
        NumberExpression numberExpression = new NumberExpression(value);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(123456789.12345, result);
    }

    @Test
    void interpret_negativeNumberInput_returnsCorrectDouble() {
        // Arrange
        String value = "-42.5";
        NumberExpression numberExpression = new NumberExpression(value);
        Context context = Context.builder().build();

        // Act
        Double result = numberExpression.interpret(context);

        // Assert
        assertEquals(-42.5, result);
    }

    @Test
    void interpret_invalidStringInput_throwsNumberFormatException() {
        // Arrange
        String value = "invalid";
        NumberExpression numberExpression = new NumberExpression(value);
        Context context = Context.builder().build();

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> numberExpression.interpret(context));
    }

    @Test
    void interpret_nullInput_throwsNullPointerException() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> new NumberExpression(null));
    }
}
