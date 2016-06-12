package com.epam.zoltannyaray.commandlinecalculator;

import java.util.List;

public interface Operator {

    Double evaluate(List<Expression> operands);

    String getSign();

    OperatorType getType();

}
