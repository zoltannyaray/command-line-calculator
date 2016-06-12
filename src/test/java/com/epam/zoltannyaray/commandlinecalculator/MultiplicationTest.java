package com.epam.zoltannyaray.commandlinecalculator;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultiplicationTest {

    private Multiplication multiplication;
    
    @BeforeMethod
    public void init() {
        multiplication = new Multiplication();
    }
    
    
    @DataProvider(name = "inputExpectedResultMap")
    public Object[][] getInputExpectedResultMap() {
        return new Object[][]{
            {Arrays.asList(new Constant(42.0)), 42.0},
            {Arrays.asList(new Constant(1.0), new Constant(41.0)), 1.0 * 41.0},
            {Arrays.asList(new Constant(-44.0), new Constant(2.0)), -44.0 * 2.0},
            {Arrays.asList(new Constant(-44.0), new Constant(2.0), new Constant(84.0)), -44.0 * 2.0 * 84.0}
        };
    }

    @Test(dataProvider = "inputExpectedResultMap")
    public void testMultiplication( List<Expression> operands, Double expectedResult) {
        Double result = multiplication.evaluate(operands);
        assertEquals(result, expectedResult);
    }
}
