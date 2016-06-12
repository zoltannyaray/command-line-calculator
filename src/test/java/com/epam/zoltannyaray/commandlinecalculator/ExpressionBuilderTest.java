package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Expression> operands;
        Expression root;
        operands = new ArrayList<Expression>();
        operands.add(new Literal(1.0));
        operands.add(new Literal(2.0));
        root = new ArithmeticExpression(new Addition(), operands);
        assertTrue(result.equals(root));
    }
    
    @Test
    public void testBuildExpression2() {
        Expression result = expressionBuilder.buildExpression("1 + 2 * 3");
        List<Expression> operands;
        Expression root;
        Expression right;
        Expression left;
        operands = new ArrayList<Expression>();
        operands.add( new Literal(2.0));
        operands.add( new Literal(3.0));
        root = new ArithmeticExpression(new Multiplication(), operands);
        right = root;
        left = new Literal(1.0);
        operands = new ArrayList<Expression>();
        operands.add(left);
        operands.add(right);
        root = new ArithmeticExpression(new Addition(), operands);
        assertTrue(result.equals(root));
    }
    
    @Test
    public void testBuildExpression3() {
        Expression result = expressionBuilder.buildExpression("(1 + 2) * 3");
        List<Expression> operands;
        Expression root;
        Expression right;
        Expression left;
        operands = new ArrayList<Expression>();
        operands.add( new Literal(1.0));
        operands.add( new Literal(2.0));
        root = new ArithmeticExpression(new Addition(), operands);
        left = root;
        right = new Literal(3.0);
        operands = new ArrayList<Expression>();
        operands.add(left);
        operands.add(right);
        root = new ArithmeticExpression(new Multiplication(), operands);
        assertTrue(result.equals(root));
    }

}
