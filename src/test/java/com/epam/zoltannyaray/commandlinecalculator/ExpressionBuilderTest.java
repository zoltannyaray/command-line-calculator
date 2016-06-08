package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExpressionBuilderTest {

    ExpressionBuilder expressionBuilder;

    @BeforeMethod
    public void init() {
        List<Operator> operatorsInPrecendenceOrderLowestFirst = new ArrayList<Operator>();
        operatorsInPrecendenceOrderLowestFirst.add(new Addition());
        operatorsInPrecendenceOrderLowestFirst.add(new Substraction());
        operatorsInPrecendenceOrderLowestFirst.add(new Multiplication());
        operatorsInPrecendenceOrderLowestFirst.add(new Division());
        OperatorPrecedenceProvider operatorPrecedenceProvider = mock(OperatorPrecedenceProvider.class);
        when(operatorPrecedenceProvider.getOperatorsInPrecedenceOrderLowestFirst()).thenReturn(operatorsInPrecendenceOrderLowestFirst);
        expressionBuilder = new ExpressionBuilder(operatorPrecedenceProvider);
    }

    @DataProvider(name = "testDataForStripInputStringExpression")
    public Object[][] getTestDataForStripInputStringExpression() {
        return new Object[][]{{"  (((1 + 2))) ", "1 + 2"}};
    }

    @Test(dataProvider = "testDataForStripInputStringExpression")
    public void testStripInputStringExpression(String input, String expected) {
        String result = expressionBuilder.stripInputStringExpression(input);
        assertEquals(result, expected);
    }

    @Test
    public void testBuildExpression1() {
        Expression result = expressionBuilder.buildExpression("1 + 2");
        assertEquals(result.getClass(), ArithmeticExpression.class);
        if (result instanceof ArithmeticExpression) {
            assertEquals(((ArithmeticExpression) result).getOperator().getClass(), Addition.class);
            assertEquals(((ArithmeticExpression) result).getOperands().size(), 2);
            assertEquals(((ArithmeticExpression) result).getOperands().get(0), new Literal(1.0));
            assertEquals(((ArithmeticExpression) result).getOperands().get(1), new Literal(2.0));
        }
    }

}
