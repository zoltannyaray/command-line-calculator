package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CompositeTest {

    ExpressionBuilder expressionBuilder;

    @BeforeMethod
    public void init() {
        OperatorPrecedenceProvider operatorPrecedenceProvider = new StandardOperatorPrecedenceProvider();
        expressionBuilder = new ExpressionBuilder(operatorPrecedenceProvider);
    }

    @DataProvider(name = "inputExpectedResultMap")
    public Object[][] getInputExpectedResultMap() {
        return new Object[][]{
            /*
            {"42", 42.0},
            {"1 + 2 * 3", 1.0 + 2.0 * 3},
            {"( 1 + 2 ) * 3", ( 1.0 + 2.0 ) * 3},
            {"5 + ( 1 + 2 ) * 3", 5.0 + ( 1.0 + 2.0 ) * 3.0},
            */
            {"(5) + (( 1 + 2 ) * 3)", (5.0) + (( 1.0 + 2.0 ) * 3.0)}
            };
    }

    @Test(dataProvider = "inputExpectedResultMap")
    public void testComposite(String input, Double expected) {
        Expression expression = expressionBuilder.buildExpression(input);
        Double actual = expression.evaluate();
        assertEquals(actual, expected);
    }

}
