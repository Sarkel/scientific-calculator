package pl.edu.wszib.kubalski.interpreter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParser;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParserFactory;
import pl.edu.wszib.kubalski.interpreter.tokenizer.Tokenizer;

import java.util.List;

@RequiredArgsConstructor
public class Interpreter {
    @NonNull
    private final Context context;

    @NonNull
    private final Tokenizer tokenizer;

    @NonNull
    private final TokenParserFactory tokenParserFactory;

    public Double interpret(String expression) {
        List<String> tokens = tokenizer.tokenize(expression);

        TokenParser tokenParser = tokenParserFactory.create(tokens);

        Expression expressionTree = tokenParser.parse();

        return expressionTree.interpret(context);
    }
}
