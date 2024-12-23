package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;

import java.util.List;

@RequiredArgsConstructor
public abstract class TokenParser {
    @NonNull
    protected final List<String> tokens;

    public abstract Expression parse();
}
