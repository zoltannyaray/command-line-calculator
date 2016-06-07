package com.epam.zoltannyaray.commandlinecalculator;

public class Literal implements Expression {

    Double value;
    
    public Literal(Double value) {
        super();
        this.value = value;
    }

    public Double evaluate() {
        return value;
    }

}
