package pl.edu.wszib.kubalski.interpreter.expression;

import pl.edu.wszib.kubalski.interpreter.Context;

public interface Expression {
    Double interpret(Context context);
}
