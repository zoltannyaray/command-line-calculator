package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExpressionIntegrationTest {

    ExpressionBuilder expressionBuilder;

    @BeforeMethod
    public void init() {
        OperatorPrecedenceProvider operatorPrecedenceProvider = new StandardOperatorPrecedenceProvider();
        expressionBuilder = new ExpressionBuilder(operatorPrecedenceProvider);
    }

    @DataProvider(name = "inputExpectedResultMap")
    public Object[][] getInputExpectedResultMap() {
        return new Object[][]{
            {"42", 42.0},
            {"1 + 2 * 3", 1.0 + 2.0 * 3},
            {"( 1 + 2 ) * 3", ( 1.0 + 2.0 ) * 3},
            {"5 + ( 1 + 2 ) * 3", 5.0 + ( 1.0 + 2.0 ) * 3.0},
            {"(5) + (( 1 + 2 ) * 3)", (5.0) + (( 1.0 + 2.0 ) * 3.0)},
            {"5", 5.0},
            {"(( 1 + 2 ) * 3)", (( 1.0 + 2.0 ) * 3.0)},
            {"2*(3+6/2)/4", 2.0*(3.0+6.0/2.0)/4.0},
            {"-10", -10.0}
        };
    }

    @Test(dataProvider = "inputExpectedResultMap")
    public void testCorrectExpression(String input, Double expected) {
        Expression expression = expressionBuilder.buildExpression(input);
        Double actual = expression.evaluate();
        assertEquals(actual, expected);
    }
    
    @DataProvider(name = "invalidInputs")
    public Object[][] getInvalidInputs() {
        return new Object[][]{
            {"42a"},
            {"(1 + 2 * 3"},
            {"()"},
            {"(*5/3)"},
            {""}
        };
    }

    @Test(dataProvider = "invalidInputs", expectedExceptions=InvalidExpressionException.class)
    public void testInvalidInput(String input) {
        Expression expression = expressionBuilder.buildExpression(input);
    }

}
