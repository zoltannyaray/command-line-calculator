package com.epam.zoltannyaray.commandlinecalculator;

public class Literal implements Expression {

    private Double value;

    public Literal(Double value) {
        super();
        this.value = value;
    }

    public Double evaluate() {
        return value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Literal && ((Literal) obj).value.equals(value)) {
            result = true;
        }
        return result;
    }

}
