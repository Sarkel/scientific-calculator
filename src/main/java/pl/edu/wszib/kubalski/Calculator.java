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

public class Calculator {
    final Context context = Context
            .builder()
            .build();

    final Interpreter interpreter = getInterpreter(context);

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Type 'exit' to quit.");

        while (true) {
            System.out.println("Provide expression to calculate:");
            String line = scanner.nextLine();

            if ("exit".equalsIgnoreCase(line)) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            try {
                Double result = interpreter.interpret(line);
                System.out.printf("Result: %f\n", result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid expression.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private Interpreter getInterpreter(Context context) {
        final ExpressionFactoryStore expressionFactoryStore = new ExpressionFactoryStore();
        final ExpressionFactoryHelper expressionFactoryHelper = new ExpressionFactoryHelper(expressionFactoryStore);
        final ExpressionFactory expressionFactory = new ExpressionFactory(expressionFactoryStore);
        final Tokenizer tokenizer = new BaseTokenizer(expressionFactoryHelper);
        final TokenParserFactory tokenParserFactory =
                new TokenParserFactory(expressionFactoryHelper, expressionFactory, BaseTokenParser.class);

        return new Interpreter(context, tokenizer, tokenParserFactory);
    }
}
