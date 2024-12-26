package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EConstantExpressionTest {

    /**
     * Tests for the EConstantExpression class.
     * <p>
     * The EConstantExpression class implements the Expression interface.
     * Its interpret method is expected to return the mathematical constant e (~2.718).
     * This test class ensures that the interpret method behaves as expected in various scenarios.
     */

    @Test
    void interpret_ShouldReturnMathematicalConstantE() {
        // Arrange
        EConstantExpression eConstantExpression = new EConstantExpression();
        Context context = Context.builder().build();

        // Act
        Double result = eConstantExpression.interpret(context);

        // Assert
        assertEquals(Math.E, result, "The interpret method should return the constant Math.E");
    }
}
