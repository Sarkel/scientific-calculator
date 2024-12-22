package pl.edu.wszib.kubalski.interpreter.operator;

import pl.edu.wszib.kubalski.interpreter.Context;

public interface Expression {
    Double interpret(Context context);
}
