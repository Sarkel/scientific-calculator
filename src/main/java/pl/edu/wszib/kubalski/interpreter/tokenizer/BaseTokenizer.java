package pl.edu.wszib.kubalski.interpreter.tokenizer;

import lombok.NonNull;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryHelper;

import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
