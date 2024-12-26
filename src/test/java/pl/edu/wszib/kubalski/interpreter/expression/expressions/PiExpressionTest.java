package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.kubalski.interpreter.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PiExpressionTest {

    /**
     * Test class for PiExpression, which represents a constant expression
     * returning the mathematical constant π (pi).
     * <p>
     * Method being tested: interpret(Context context).
     */
    @Test
    void shouldReturnMathPIWhenInterpretIsCalled() {
        // Arrange
        PiExpression piExpression = new PiExpression();
        Context context = Context.builder().build();

        // Act
        Double result = piExpression.interpret(context);

        // Assert
        assertEquals(Math.PI, result, "The interpret method should return the value of PI");
    }
}
