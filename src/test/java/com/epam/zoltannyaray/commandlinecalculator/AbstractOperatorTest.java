package com.epam.zoltannyaray.commandlinecalculator;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class AbstractOperatorTest {

    @Test
    public void testEqualsIsTrueOnSameOperator() {
        Operator operator1 = new Addition();
        Operator operator2 = new Addition();
        assertEquals(operator1, operator2);
    }
    
    @Test
    public void testEqualsIsTrueOnDifferentOperator() {
        Operator operator1 = new Addition();
        Operator operator2 = new Multiplication();
        assertNotEquals(operator1, operator2);
    }
}
