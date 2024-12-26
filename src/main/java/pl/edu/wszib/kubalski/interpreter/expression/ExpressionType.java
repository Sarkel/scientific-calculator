package pl.edu.wszib.kubalski.interpreter.expression;

import lombok.Getter;


/**
 * Enum representing different types of expressions used in an interpreter pattern.
 * Each expression type is associated with an operator and the number of arguments it requires.
 * Expressions can represent mathematical operations, functions, constants, or numbers.
 * <p>
 * The {@code operator} field specifies the representation of the operation in string form,
 * such as "+" for addition or "sin" for the sine function.
 * <p>
 * The {@code numberOfArguments} field indicates how many arguments the operation requires:
 * - 0 for constants and numbers
 * - 1 for unary operations like negation or trigonometric functions
 * - 2 for binary operations like addition or multiplication
 * <p>
 * This enum is commonly used together with factories to create specific expression instances
 * based on the type of operator and operand requirements.
 */
@Getter
public enum ExpressionType {
    ADD("+", "Addition", 2, ExpressionGroup.ARITHMETIC),
    COS("cos", "Cosine", 1, ExpressionGroup.TRIGONOMETRY),
    DIVIDE("/", "Division", 2, ExpressionGroup.ARITHMETIC),
    LOG("log", "Natural logarithm", 1, ExpressionGroup.ARITHMETIC),
    MULTIPLY("*", "Multiplication", 2, ExpressionGroup.ARITHMETIC),
    NUMBER("", "Any real number", 0, ExpressionGroup.UNITARY, false),
    PI("pi", "pi", 0, ExpressionGroup.CONSTANT),
    POW("^", "Exponentiation", 2, ExpressionGroup.ARITHMETIC),
    SIN("sin", "Sine", 1, ExpressionGroup.TRIGONOMETRY),
    SQRT("sqrt", "Elementation", 1, ExpressionGroup.ARITHMETIC),
    SUBTRACT("-", "Substraction", 2, ExpressionGroup.ARITHMETIC),
    TAN("tan", "Tangent", 1, ExpressionGroup.TRIGONOMETRY),
    NEGATE("-", "Negation", 1, ExpressionGroup.UNITARY) {
        @Override
        public String getEquation() {
            return String.format("(%sx)", this.getOperator());
        }
    },
    POSITIVE("+", "Positive", 1, ExpressionGroup.UNITARY, false) {
        @Override
        public String getEquation() {
            return "x";
        }
    },
    E("e", "e", 0, ExpressionGroup.CONSTANT),
    CTAN("ctan", "Cotangent", 1, ExpressionGroup.TRIGONOMETRY);

    private final String operator;

    private final String descriptiveName;

    private final int numberOfArguments;

    private final boolean printable;

    private final ExpressionGroup group;

    ExpressionType(String operator, String descriptiveName, int numberOfArguments, ExpressionGroup group) {
        this(operator, descriptiveName, numberOfArguments, group, true);
    }

    ExpressionType(String operator, String descriptiveName, int numberOfArguments, ExpressionGroup group, boolean printable) {
        this.operator = operator;
        this.descriptiveName = descriptiveName;
        this.numberOfArguments = numberOfArguments;
        this.printable = printable;
        this.group = group;
    }

    public String getEquation() {
        return switch (numberOfArguments) {
            case 1 -> String.format("%s(x)", this.getOperator());
            case 2 -> String.format("x %s y", this.getOperator());
            default -> "";
        };
    }

    @Override
    public String toString() {
        String equation = this.getEquation();
        return this.descriptiveName + (equation.isEmpty() ? "" : String.format(": %s", equation));
    }
}
