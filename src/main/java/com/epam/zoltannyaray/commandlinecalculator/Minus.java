package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public class Minus extends AbstractOperator {

    public Double evaluate(List<Expression> operands) {
        return -1.0 * operands.get(0).evaluate();
    }

    public String getSign() {
        return "-";
    }

    public OperatorType getType() {
        return OperatorType.UNARY_PREFIX;
    }

}
