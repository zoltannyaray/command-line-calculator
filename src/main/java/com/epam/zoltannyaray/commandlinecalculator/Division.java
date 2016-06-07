package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public class Division implements Operator {

    private Division() {
    }

    private static class SingletonHolder {
        public static final Division INSTANCE = new Division();
    }
    
    public static Division getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public Double evaluate(List<Expression> operands) {
        Expression firstOperand = operands.get(0);
        Double result = firstOperand.evaluate();
        for( int i = 1; i < operands.size(); i++ ) {
            // todo: div by zero?
            result /= operands.get(i).evaluate();
        }
        return result;
    }

    public String getSign() {
        return "/";
    }

    public OperatorType getType() {
        return OperatorType.BINARY;
    }
    
    
}
