package pl.edu.wszib.kubalski.interpreter.operator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExpressionType {
    ADD("+", 2),
    COS("cos", 1),
    DIVIDE("/", 2),
    LOG("log", 1),
    MULTIPLY("*", 2),
    NUMBER("", 0),
    PI("pi", 0),
    POW("^", 2),
    SIN("sin", 1),
    SQRT("sqrt", 1),
    SUBTRACT("-", 2),
    TAN("tan", 1),
    NEGATE("-", 1),
    POSITIVE("+", 1);


    private final String operator;

    private final int numberOfArguments;
}
