package pl.edu.wszib.kubalski.interpreter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.Expression;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParser;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParserFactory;
import pl.edu.wszib.kubalski.interpreter.tokenizer.Tokenizer;

import java.util.List;

/**
 * The Interpreter class is responsible for evaluating mathematical or logical
 * expressions provided as strings. It leverages the tokenizer, parser, and context
 * components to perform this evaluation.
 *
 * The class takes an input expression in string format, tokenizes it into smaller
 * components using the provided Tokenizer, parses the tokens into an expression
 * tree using a TokenParser, and finally interprets the expression tree to produce
 * a result.
 *
 * This design separates concerns into three main tasks:
 * 1. Tokenization of the expression.
 * 2. Parsing the tokens into an expression tree.
 * 3. Evaluating the parsed expression using the context.
 *
 * Dependencies:
 * - Context: Provides any additional state or information needed during interpretation.
 * - Tokenizer: Breaks down the input expression into manageable tokens.
 * - TokenParserFactory: Creates a TokenParser to convert tokens into an expression tree.
 *
 * Methods:
 * - interpret: Main method to interpret an input expression string and return the result
 *   as a Double value. It uses all the provided components to complete the evaluation.
 */
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
