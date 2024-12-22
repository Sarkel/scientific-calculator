package pl.edu.wszib.kubalski.interpreter.operator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExpressionType {
    ADD("+"),
    COS("cos"),
    DIVIDE("/"),
    LOG("log"),
    MULTIPLY("*"),
    NUMBER(""),
    PI("pi"),
    POW("^"),
    SIN("sin"),
    SQRT("sqrt"),
    SUBTRACT("-"),
    TAN("tan");


    private final String operator;
}
