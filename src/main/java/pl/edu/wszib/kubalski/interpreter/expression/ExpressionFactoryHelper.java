package pl.edu.wszib.kubalski.interpreter.expression;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

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
