package com.epam.zoltannyaray.commandlinecalculator;

public abstract class AbstractOperator implements Operator {

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ( obj instanceof Operator ) {
            Operator castedObj = (Operator) obj;
            if ( castedObj.getClass().equals(this.getClass())) {
                result = true;
            }
        }
        return result;
    }
    
    
}
