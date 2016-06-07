package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExpressionBuilderTest {
  
    ExpressionBuilder expressionBuilder;
    
    @BeforeMethod
    public void init() {
        OperatorPrecedenceProvider operatorPrecedenceProvider = new StandardOperatorPrecedenceProvider();
        expressionBuilder = new ExpressionBuilder(operatorPrecedenceProvider);
    }
    
    @DataProvider(name="testDataForStripInputStringExpression")
    public Object[][] getTestDataForStripInputStringExpression() {
        return new Object[][]{
            {"  (((1 + 2))) ", "1 + 2"}
        };
    }
    
    @Test(dataProvider="testDataForStripInputStringExpression")
    public void testStripInputStringExpression( String input, String expected ) {
        String result = expressionBuilder.stripInputStringExpression(input);
        assertEquals(result, expected);
    }
    
    
}
