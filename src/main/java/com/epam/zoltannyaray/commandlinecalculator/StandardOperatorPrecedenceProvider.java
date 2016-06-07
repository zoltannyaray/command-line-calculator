package com.epam.zoltannyaray.commandlinecalculator;

import java.util.Arrays;
import java.util.List;

public class StandardOperatorPrecedenceProvider implements OperatorPrecedenceProvider {

    public List<Operator> getOperatorsInPrecedenceOrderLowestFirst() {
        return Arrays.asList(Addition.getInstance(), Substraction.getInstance(), Multiplication.getInstance(), Division.getInstance());
    }

}
