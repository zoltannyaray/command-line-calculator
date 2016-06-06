package com.epam.zoltannyaray.commandlinecalculator;

public class StringArithmeticExpressionEvaluator {

    private StringTokenizer stringExpressionTokenizer;
    
    public StringArithmeticExpressionEvaluator(StringTokenizer stringExpressionTokenizer) {
        super();
        this.stringExpressionTokenizer = stringExpressionTokenizer;
    }

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
