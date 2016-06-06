package com.epam.zoltannyaray.commandlinecalculator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringArithmeticExpressionEvaluatorTest {

    private StringArithmeticExpressionEvaluator evaluator;

    @BeforeMethod
    public void init() {
        evaluator = new StringArithmeticExpressionEvaluator();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateExpressionShouldThrowIllegalArgumentExceptionOnNull() {
        evaluator.evaluateExpression(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateExpressionShouldThrowIllegalArgumentExceptionOnEmptyString() {
        evaluator.evaluateExpression("");
    }
}
