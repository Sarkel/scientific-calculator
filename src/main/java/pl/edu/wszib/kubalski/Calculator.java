package pl.edu.wszib.kubalski;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.Interpreter;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactory;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryHelper;
import pl.edu.wszib.kubalski.interpreter.expression.factory.ExpressionFactoryStore;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParser;
import pl.edu.wszib.kubalski.interpreter.parser.TokenParserFactory;
import pl.edu.wszib.kubalski.interpreter.tokenizer.BaseTokenizer;
import pl.edu.wszib.kubalski.interpreter.tokenizer.Tokenizer;

import java.util.Scanner;


/**
 * The Calculator class provides a user interface for evaluating mathematical expressions
 * entered as text and printing the results to the console. The user can type expressions
 * to calculate, and the program will evaluate and display the results. Typing "exit"
 * allows the user to terminate the program.
 *
 * Responsibilities:
 * - Provides a REPL (Read-Eval-Print Loop) environment for entering expressions.
 * - Validates and interprets user input using the Interpreter class.
 * - Handles user input errors gracefully and allows repeated attempts to evaluate expressions.
 *
 * Key Methods:
 * - start(): Launches the user interface for receiving expressions and calculating results.
 *
 * Dependencies:
 * - Context: A shared state container used to configure and initialize the Interpreter.
 * - Interpreter: Interprets and evaluates mathematical expressions provided by the user.
 * - Tokenizer, TokenParserFactory: Used internally by the Interpreter for expression evaluation.
 *
 * Error Handling:
 * - If the user enters invalid input, the program provides a message indicating the input is
 *   invalid and prompts the user to try again.
 * - In case of unexpected errors during evaluation, an error message is displayed.
 */
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
                new TokenParserFactory(expressionFactoryHelper, expressionFactory, TokenParser.class);

        return new Interpreter(context, tokenizer, tokenParserFactory);
    }
}
