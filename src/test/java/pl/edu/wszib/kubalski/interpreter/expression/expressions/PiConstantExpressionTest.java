package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for PiConstantExpression.
 * <p>
 * This class is responsible for testing the `interpret` method
 * of the PiConstantExpression class, which returns the mathematical
 * constant PI (3.141592653589793) when evaluated in any given context.
 */
public class PiConstantExpressionTest {

    @Test
    void shouldReturnPi_whenInterpretIsCalled() {
        // Arrange
        PiConstantExpression piConstantExpression = new PiConstantExpression();
        Context context = Context.builder().build();

        // Act
        Double result = piConstantExpression.interpret(context);

        // Assert
        assertEquals(Math.PI, result, "The interpret method should return the value of Math.PI");
    }
}
