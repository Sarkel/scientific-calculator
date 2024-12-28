package pl.edu.wszib.kubalski.interpreter.expression.expressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CtanExpressionTest {

    /**
     * Tests for the CtanExpression class.
     * <p>
     * The CtanExpression class represents the mathematical cotangent operation,
     * which is the inverse of the tangent (1 / tan(x)).
     * Its `interpret` method takes a `Context` and evaluates the cotangent
     * based on the value provided by the `Expression` passed to the constructor.
     * <p>
     * Tested scenarios:
     * - Valid cotangent calculation for a positive tan(x) result.
     * - Valid cotangent calculation for a negative tan(x) result.
     * - ArithmeticException when attempting to calculate cotangent for tan(x) = 0.
     * - Handle scenario where tan(x) result produces an infinite cotangent.
     */

    @Test
    void shouldReturnValidCotan_WhenTanIsPositive() {
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);

        when(mockExpression.interpret(context)).thenReturn(1.0); // tan(x) = 1.0

        CtanExpression ctanExpression = new CtanExpression(mockExpression);
        Double result = ctanExpression.interpret(context);

        assertEquals(0.6421, result, 0.0001);
    }

    @Test
    void shouldReturnValidCotan_WhenTanIsNegative() {
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);

        when(mockExpression.interpret(context)).thenReturn(-1.0); // tan(x) = -1.0

        CtanExpression ctanExpression = new CtanExpression(mockExpression);
        Double result = ctanExpression.interpret(context);

        assertEquals(-0.6421, result, 0.0001);
    }

    @Test
    void shouldThrowArithmeticException_WhenTanIsZero() {
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);

        when(mockExpression.interpret(context)).thenReturn(0.0); // tan(x) = 0.0

        CtanExpression ctanExpression = new CtanExpression(mockExpression);

        assertThrows(ArithmeticException.class, () -> ctanExpression.interpret(context),
                "Calculating cotan for tan(x) = 0.0 should throw ArithmeticException");
    }

    @Test
    void shouldReturnZero_WhenTanIsInfinity() {
        Context context = Context.builder().build();
        Expression mockExpression = Mockito.mock(Expression.class);

        when(mockExpression.interpret(context)).thenReturn(Double.POSITIVE_INFINITY); // tan(x) = +Infinity

        CtanExpression ctanExpression = new CtanExpression(mockExpression);

        assertThrows(ArithmeticException.class, () -> ctanExpression.interpret(context));
    }
}
