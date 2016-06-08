package com.epam.zoltannyaray.commandlinecalculator;

import java.util.ArrayList;
import java.util.List;

public class StandardOperatorPrecedenceProvider implements OperatorPrecedenceProvider {

    public List<Operator> getOperatorsInPrecedenceOrderLowestFirst() {
        List<Operator> operators = new ArrayList<Operator>();
        operators.add(new Addition());
        operators.add(new Substraction());
        operators.add(new Multiplication());
        operators.add(new Division());
        return operators;
    }

}
