package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public class Addition implements Operator {

    private Addition() {
    }

    private static class SingletonHolder {
        public static final Addition INSTANCE = new Addition();
    }
    
    public static Addition getInstance() {
        return SingletonHolder.INSTANCE;
    }

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
