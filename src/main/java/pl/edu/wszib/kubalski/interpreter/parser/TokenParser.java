package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryHelper;

import java.util.List;

@RequiredArgsConstructor
public abstract class TokenParser {
    @NonNull
    protected final List<String> tokens;

    @NonNull
    protected final ExpressionFactoryHelper expressionFactoryHelper;

    @NonNull
    protected final ExpressionFactory expressionFactory;

    public abstract Expression parse();
}
