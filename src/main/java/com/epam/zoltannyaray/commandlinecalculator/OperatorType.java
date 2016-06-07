package com.epam.zoltannyaray.commandlinecalculator;

import java.util.regex.Pattern;

public enum OperatorType {

    BINARY(true, true), UNARY_PREFIX(false, true), UNARIY_POSTFIX(true, false);
    
    private boolean isOperandNeededOnLeftSide;
    

    private boolean isOperandNeededOnRightSide;
    
    private OperatorType(boolean isOperandNeededOnLeftSide, boolean isOperandNeededOnRightSide) {
        this.isOperandNeededOnLeftSide = isOperandNeededOnLeftSide;
        this.isOperandNeededOnRightSide = isOperandNeededOnRightSide;
    }
    
    public boolean isOperandNeededOnLeftSide() {
        return isOperandNeededOnLeftSide;
    }

    public boolean isOperandNeededOnRightSide() {
        return isOperandNeededOnRightSide;
    }

    public String getExpressionRegexByOperatorSign( String operatorSign ) {
        String expressionRegex = "";
        if ( isOperandNeededOnLeftSide ) {
            expressionRegex += ".+";
        }
        expressionRegex += Pattern.quote(operatorSign);
        if ( isOperandNeededOnRightSide ) {
            expressionRegex += ".+";
        }
        return expressionRegex;
    }
    
}
