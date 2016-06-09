package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;


public class Multiplication extends AbstractOperator {

    public Double evaluate(List<Expression> operands) {
        Expression firstOperand = operands.get(0);
        Double result = firstOperand.evaluate();
        for( int i = 1; i < operands.size(); i++ ) {
            result *= operands.get(i).evaluate();
        }
        return result;
    }

    public String getSign() {
        return "*";
    }

    public OperatorType getType() {
        return OperatorType.BINARY;
    }
    
}
