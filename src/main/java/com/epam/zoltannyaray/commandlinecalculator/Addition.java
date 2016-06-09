package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public class Addition extends AbstractOperator {

    public Double evaluate(List<Expression> operands) {
        Double result = 0.0;
        for (Expression operand : operands) {
            result += operand.evaluate();
        }
        return result;
    }

    public String getSign() {
        return "+";
    }

    public OperatorType getType() {
        return OperatorType.BINARY;
    }

}
