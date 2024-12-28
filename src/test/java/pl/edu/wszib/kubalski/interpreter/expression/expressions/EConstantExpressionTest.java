package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;

import static org.junit.jupiter.api.Assertions.*;

class EConstantExpressionTest {

    /**
     * This test class ensures the correctness of the {@link EConstantExpression#interpret(Context)} method.
     * The method should consistently return the value of Math.E, representing Euler's number, regardless of the provided {@link Context}.
     */

    @Test
    void testInterpretReturnsEulerConstant() {
        // Arrange
        Context context = Context.builder().build();
        EConstantExpression expression = new EConstantExpression();

        // Act
        Double result = expression.interpret(context);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(Math.E, result, 1e-9, "The result should be equal to Math.E");
    }
}
