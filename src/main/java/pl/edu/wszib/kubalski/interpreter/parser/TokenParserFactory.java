package pl.edu.wszib.kubalski.interpreter.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiredArgsConstructor
public class TokenParserFactory {
    @NonNull
    private final Class<? extends TokenParser> tokenParserPrototype;

    public TokenParser create(List<String> tokens) {
        try {
            return tokenParserPrototype.getDeclaredConstructor(List.class).newInstance(tokens);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
