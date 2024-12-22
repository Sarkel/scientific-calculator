package pl.edu.wszib.kubalski.interpreter.tokenizer;

import java.util.List;

public interface Tokenizer {
    List<String> tokenize(String expression);
}
