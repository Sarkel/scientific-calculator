package pl.edu.wszib.kubalski;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.Interpreter;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryHelper;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionFactoryStore;
import pl.edu.wszib.kubalski.interpreter.parser.BaseTokenParser;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParserFactory;
import pl.edu.wszib.kubalski.interpreter.tokenizer.BaseTokenizer;
import pl.edu.wszib.kubalski.interpreter.tokenizer.Tokenizer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Context context = Context
                .builder()
                .build();

        final Interpreter interpreter = getInterpreter(context);

        do {
            System.out.println("Provide expression to calculate:");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            Double result = interpreter.interpret(line);

            System.out.printf("Result: %f\n", result);
        } while (true);
    }

    private static Interpreter getInterpreter(Context context) {
        final ExpressionFactoryStore expressionFactoryStore = new ExpressionFactoryStore();
        final ExpressionFactoryHelper expressionFactoryHelper = new ExpressionFactoryHelper(expressionFactoryStore);
        final ExpressionFactory expressionFactory = new ExpressionFactory(expressionFactoryStore);
        final Tokenizer tokenizer = new BaseTokenizer(expressionFactoryHelper);
        final TokenParserFactory tokenParserFactory =
                new TokenParserFactory(expressionFactoryHelper, expressionFactory, BaseTokenParser.class);

        return new Interpreter(context, tokenizer, tokenParserFactory);
    }
}
