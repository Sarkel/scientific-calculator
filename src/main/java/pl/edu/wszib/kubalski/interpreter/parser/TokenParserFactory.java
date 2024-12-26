package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * The TokenParserFactory class is responsible for creating instances of the TokenParser class
 * using the provided prototype, expression factory, and helper objects.
 * This factory encapsulates the construction logic for TokenParser and helps instantiate
 * TokenParser objects with required dependencies dynamically.
 *
 * Responsibilities of this class include:
 * - Providing a method to create TokenParser instances with a specific set of tokens.
 * - Ensuring that the TokenParser is instantiated with its required dependencies such as
 *   ExpressionFactoryHelper and ExpressionFactory.
 * - Handling reflection-based construction of TokenParser instances.
 *
 * The TokenParserFactory requires:
 * - An implementation of the TokenParser class (provided as a prototype class).
 * - An ExpressionFactory to create expressions.
 * - An ExpressionFactoryHelper to assist in expression type handling and operator identification.
 *
 * The create method accepts a list of tokens and returns a new TokenParser instance that can
 * process those tokens. Errors during instantiation are wrapped and re-thrown as a RuntimeException.
 */
@RequiredArgsConstructor
public class TokenParserFactory {
    @NonNull
    protected final ExpressionFactoryHelper expressionFactoryHelper;

    @NonNull
    protected final ExpressionFactory expressionFactory;

    @NonNull
    private final Class<? extends TokenParser> tokenParserPrototype;

    public TokenParser create(List<String> tokens) {
        try {
            return tokenParserPrototype.getDeclaredConstructor(List.class, ExpressionFactoryHelper.class, ExpressionFactory.class)
                    .newInstance(tokens, expressionFactoryHelper, expressionFactory);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
