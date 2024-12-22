package pl.edu.wszib.kubalski;

import pl.edu.wszib.kubalski.interpreter.Context;
import pl.edu.wszib.kubalski.interpreter.Interpreter;
import pl.edu.wszib.kubalski.interpreter.operator.ExpressionFactory;
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

        final ExpressionFactory expressionFactory = new ExpressionFactory();
        final Tokenizer tokenizer = new BaseTokenizer(expressionFactory);
        final TokenParserFactory tokenParserFactory = new TokenParserFactory(BaseTokenParser.class);

        final Interpreter interpreter = new Interpreter(context, tokenizer, tokenParserFactory);

        do {
            System.out.println("Provide expression to calculate:");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            Double result = interpreter.interpret(line);

            System.out.printf("Result: %f\n", result);
        } while (true);
    }
}
