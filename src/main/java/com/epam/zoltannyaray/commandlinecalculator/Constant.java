package com.epam.zoltannyaray.commandlinecalculator;

public class Constant implements Expression {

    private Double value;

    public Constant(Double value) {
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
        if (obj instanceof Constant && ((Constant) obj).value.equals(value)) {
            result = true;
        }
        return result;
    }

}
