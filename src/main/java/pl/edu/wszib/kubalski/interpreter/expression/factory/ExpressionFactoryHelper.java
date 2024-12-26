package pl.edu.wszib.kubalski.interpreter.expression.factory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wszib.kubalski.interpreter.expression.ExpressionType;

import java.util.Set;

/**
 * Helper class for retrieving types of expressions available in the {@link ExpressionFactoryStore}.
 * This class provides methods for fetching expressions classified into functional, low-priority,
 * high-priority, constant, and unary categories.
 *
 * The methods return arrays of string representations of operators associated with specific
 * expression types stored in the {@link ExpressionFactoryStore}.
 *
 * The class is constructed with an instance of {@link ExpressionFactoryStore}, which acts
 * as the source of expression type mappings.
 */
@RequiredArgsConstructor
public class ExpressionFactoryHelper {
    @NonNull
    private final ExpressionFactoryStore store;

    public String[] getFunctionalExpressions() {
        return getExpressionTypes(store.getFunctionalExpressions().keySet());
    }

    public String[] getLowPriorityExpressions() {
        return getExpressionTypes(store.getLowPriorityExpressions().keySet());
    }

    public String[] getHighPriorityExpressions() {
        return getExpressionTypes(store.getHighPriorityExpressions().keySet());
    }

    public String[] getConstantExpressions() {
        return getExpressionTypes(store.getConstantExpressions().keySet());
    }

    public String[] getUnaryExpressions() {
        return getExpressionTypes(store.getUnaryExpressions().keySet());
    }

    private String[] getExpressionTypes(Set<ExpressionType> expressionTypes) {
        return expressionTypes.stream().map(ExpressionType::getOperator).toArray(String[]::new);
    }
}
