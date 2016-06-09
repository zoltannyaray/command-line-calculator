package com.epam.zoltannyaray.commandlinecalculator;

import java.util.ArrayList;
import java.util.List;

import org.mockito.cglib.core.CollectionUtils;

public class ArithmeticExpression implements Expression {

    private List<Expression> operands;
    
    private Operator operator;
    
    public ArithmeticExpression( Operator operator, List<Expression> operands) {
        super();
        this.operator = operator;
        this.operands = operands;
    }
    
    public Double evaluate() {
        return operator.evaluate(operands);
    }
    
    public List<Expression> getOperands() {
        return new ArrayList<Expression>(operands);
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ArithmeticExpression ) {
            ArithmeticExpression castedObj = (ArithmeticExpression) obj;
            if ( castedObj.operator.equals(operator)) {
                result = true;
            }
        }
        return result;
    }

    
    
}
