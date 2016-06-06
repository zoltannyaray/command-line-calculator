package com.epam.zoltannyaray.commandlinecalculator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.List;

public class ArithmeticExpressionStringTokenizerTest {

    private ArithmeticExpressionStringTokenizer tokenizer;

    @BeforeMethod
    public void init() {
        tokenizer = new ArithmeticExpressionStringTokenizer();
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testTokenizeShouldThrowIllegalArgumentExceptionOnNull() {
        tokenizer.tokenize(null);
    }
    
    @Test
    public void testTokenizeShouldReturnEmptyListOnEmptyString() {
        List<String> result = tokenizer.tokenize("");
        assertTrue(result != null,"result should not be null");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTokenizeShouldTokenizeSingleNumber() {
        List<String> result = tokenizer.tokenize("12");
        assertEquals(result, Arrays.asList("12"));
    }
    
    @Test
    public void testTokenizerShouldTokenizeBinaryExpression() {
        List<String> result = tokenizer.tokenize("12 +10");
        assertEquals(result, Arrays.asList("12", "+", "10"));
    }
    
    @Test
    public void testTokenizerShouldTokenizeComplexExpression() {
        List<String> result = tokenizer.tokenize("12 +10/  32 * 1100");
        assertEquals(result, Arrays.asList("12", "+", "10", "/", "32", "*", "1100"));
    }

}
