package pl.edu.wszib.kubalski.interpreter.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * Enum representing different types of expressions used in an interpreter pattern.
 * Each expression type is associated with an operator and the number of arguments it requires.
 * Expressions can represent mathematical operations, functions, constants, or numbers.
 *
 * The {@code operator} field specifies the representation of the operation in string form,
 * such as "+" for addition or "sin" for the sine function.
 *
 * The {@code numberOfArguments} field indicates how many arguments the operation requires:
 * - 0 for constants and numbers
 * - 1 for unary operations like negation or trigonometric functions
 * - 2 for binary operations like addition or multiplication
 *
 * This enum is commonly used together with factories to create specific expression instances
 * based on the type of operator and operand requirements.
 */
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
    POSITIVE("+", 1),
    E("e", 0);

    private final String operator;

    private final int numberOfArguments;
}
