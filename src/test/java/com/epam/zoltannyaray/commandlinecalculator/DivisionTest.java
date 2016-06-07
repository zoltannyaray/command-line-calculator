package com.epam.zoltannyaray.commandlinecalculator;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DivisionTest {

    private Division division;
    
    @BeforeMethod
    public void init() {
        division = Division.getInstance();
    }
    
    
    @DataProvider(name = "inputExpectedResultMap")
    public Object[][] getInputExpectedResultMap() {
        return new Object[][]{
            {Arrays.asList(new Literal(42.0), new Literal(3.0)), 42.0/3.0}
        };
    }

    @Test(dataProvider = "inputExpectedResultMap")
    public void testDivision( List<Expression> operands, Double expectedResult) {
        Double result = division.evaluate(operands);
        assertEquals(result, expectedResult);
    }
}
