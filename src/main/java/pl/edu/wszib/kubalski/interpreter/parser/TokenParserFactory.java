package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
