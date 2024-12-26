package pl.edu.wszib.kubalski.interpreter.tokenizer;

import lombok.NonNull;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryHelper;

import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The BaseTokenizer class is responsible for parsing and tokenizing mathematical or
 * logical expressions represented as strings into a list of discrete string tokens.
 *
 * It implements the Tokenizer interface and provides an efficient tokenization
 * mechanism that identifies different elements such as numbers, operators,
 * functions, constants, and parentheses in the given expression.
 *
 * Core functionalities:
 * - Tokenizes an expression string into a list of tokens.
 * - Handles numbers (both integers and decimals).
 * - Handles operators with high or low priority.
 * - Recognizes functions and constants.
 *
 * Internals:
 * - Utilizes regular expressions to identify and split components of an expression.
 * - Builds its tokenization patterns dynamically using provided operator and function definitions
 *   from the associated ExpressionFactoryHelper.
 *
 * Responsibilities:
 * - Parse and break down an expression into separate tokens for further evaluation.
 * - Utilize a flexible and extensible approach to handle various mathematical or logical elements.
 *
 * Dependencies:
 * - Requires an instance of ExpressionFactoryHelper to dynamically construct regular expressions
 *   for operators, functions, and constants.
 *
 * Key Methods:
 * - tokenize(String expression): Extracts and returns a list of tokens representing the components
 *   of the given expression.
 * - buildOperatorRegex(): Constructs a regex pattern to match supported operators and parentheses.
 * - buildFunctionRegex(): Constructs a regex pattern to match supported functions and constants.
 * - buildPattern(): Combines all sub-patterns (numbers, operators, functions) into a single regex
 *   pattern for comprehensive tokenization.
 */
public class BaseTokenizer implements Tokenizer {
    private static final String NUMBER_REGEX = "\\d+(\\.\\d+)?";  // Matches integers and decimals

    private final Pattern tokenPattern;

    @NonNull
    private final ExpressionFactoryHelper expressionFactoryHelper;

    public BaseTokenizer(@NonNull ExpressionFactoryHelper expressionFactoryHelper) {
        this.expressionFactoryHelper = expressionFactoryHelper;

        tokenPattern = buildPattern();
    }

    @Override
    public List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = tokenPattern.matcher(expression);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    private String buildOperatorRegex() {
        String operators = Stream.of(
                        expressionFactoryHelper.getHighPriorityExpressions(),
                        expressionFactoryHelper.getLowPriorityExpressions()
                )
                .flatMap(Arrays::stream)
                .map(Pattern::quote)
                .collect(Collectors.joining());

        return String.format("[%s%s%s]", operators, "(", ")");
    }

    private String buildFunctionRegex() {
        return Stream.of(
                        expressionFactoryHelper.getFunctionalExpressions(),
                        expressionFactoryHelper.getConstantExpressions()
                )
                .flatMap(Arrays::stream)
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }

    private Pattern buildPattern() {
        return Pattern.compile(NUMBER_REGEX + "|" + buildOperatorRegex() + "|" + buildFunctionRegex());
    }
}
