package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public interface OperatorPrecedenceProvider {

    List<Operator> getOperatorsInPrecedenceOrderLowestFirst();
    
}
