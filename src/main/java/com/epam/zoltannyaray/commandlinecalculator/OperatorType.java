package com.epam.zoltannyaray.commandlinecalculator;

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

}
