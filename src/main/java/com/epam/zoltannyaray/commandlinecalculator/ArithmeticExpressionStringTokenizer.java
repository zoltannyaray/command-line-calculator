package com.epam.zoltannyaray.commandlinecalculator;

public class ArithmeticExpressionStringTokenizer extends RegexStringTokenizer {

    public ArithmeticExpressionStringTokenizer() {
        super("(\\+|\\-|\\*|\\/|\\d+)");
    }

    
    
}
