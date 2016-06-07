package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public class ArithmeticExpression implements Expression {

    List<Expression> operands;
    
    public ArithmeticExpression( Operator operator, List<Expression> operands) {
        super();
        this.operator = operator;
        this.operands = operands;
    }

    Operator operator;
    
    public Double evaluate() {
        return operator.evaluate(operands);
    }
    
}
