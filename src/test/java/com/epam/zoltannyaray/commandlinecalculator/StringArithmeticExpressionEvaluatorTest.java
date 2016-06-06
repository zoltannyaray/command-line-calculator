package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringArithmeticExpressionEvaluatorTest {

    private StringArithmeticExpressionEvaluator evaluator;

    @BeforeMethod
    public void init() {
        StringTokenizer stringExpressionTokenizer = new ArithmeticExpressionStringTokenizer();
        evaluator = new StringArithmeticExpressionEvaluator( stringExpressionTokenizer );
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateExpressionShouldThrowIllegalArgumentExceptionOnNull() {
        evaluator.evaluateExpression(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateExpressionShouldThrowIllegalArgumentExceptionOnEmptyString() {
        evaluator.evaluateExpression("");
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateExpressionShouldEvaluateSingleNumber() {
        Integer result = evaluator.evaluateExpression("13");
        assertEquals(result, Integer.valueOf(13));
    } 
    
    // ((11)+2*5)+((3))*(1+2 USD)
    // ParenthesesExpressionParser
    // Exp((11)+2*5) + Exp((3)) * Exp(1+2 USD)
    // MultiplicationExpressionParser
    // Exp((11)+2*5) + Mult(Exp((3)), Exp(1+2 USD))
    // AdditionExpressionParser
    // Add(Exp((11)+2*5) + Mult(Exp((3)), Exp(1+2 USD)))
    
    // (11)+2*5 + (3) * 1+2 USD
    // (11)+Mul(2,5) + Mul((3), 1+2 USD)
    // Add((11), Mul(2,5)) + Mul((3), 1+2 USD)
    // Add(Add((11), Mul(2,5)), Mul((3), 1+2 USD))
    
    
    
    // 1+2*3
}
