package com.epam.zoltannyaray.commandlinecalculator;

public class StringArithmeticExpressionEvaluator {

    public Integer evaluateExpression( String expression ) {
        if ( expression == null ) {
            throw new IllegalArgumentException("Expression should not be null");
        }
        if ( expression.isEmpty() ) {
            throw new IllegalArgumentException("Expression should not be empty");
        }
        Integer result = 0;
        return result;
    }
    
}
