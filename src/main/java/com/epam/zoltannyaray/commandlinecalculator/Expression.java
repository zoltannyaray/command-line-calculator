package com.epam.zoltannyaray.commandlinecalculator;

import java.util.LinkedList;
import java.util.List;

public class Expression implements ExpressionPart {

    List<String> expressionParts;

    public Expression(List<String> expressionParts) {
        super();
        this.expressionParts = expressionParts;
    }
    
}
