package com.epam.zoltannyaray.commandlinecalculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionBuilder {

    private static final String EXPRESSION_STRIP_REGEX = "^\\s*\\(\\s*(.+)\\s*\\)\\s*$";
    private static final String OPENING_PARENTHESES = "(";
    private static final String CLOSING_PARENTHESES = ")";
    private static final String PARENTHESES_REGEX = "[\\" + OPENING_PARENTHESES + "\\" + CLOSING_PARENTHESES + "]";
    private OperatorPrecedenceProvider operatorPrecedenceProvider;

    public ExpressionBuilder(OperatorPrecedenceProvider operatorPrecedenceProvider) {
        super();
        this.operatorPrecedenceProvider = operatorPrecedenceProvider;
    }

    public Expression buildExpression(String input) {
        String strippedInput = stripInputStringExpression(input);
        Iterator<Operator> operatorsInPrecedenceOrderLowestFirst = operatorPrecedenceProvider.getOperatorsInPrecedenceOrderLowestFirst().iterator();
        Pattern expressionPatternByOperator;
        List<Expression> operands = new ArrayList<Expression>();
        boolean rootExpressionFound = false;
        boolean isExpressionCorrect = false;
        Operator operator = null;
        while (!rootExpressionFound && operatorsInPrecedenceOrderLowestFirst.hasNext()) {
            operator = operatorsInPrecedenceOrderLowestFirst.next();
            expressionPatternByOperator = Pattern.compile(Pattern.quote(operator.getSign()));
            Matcher matcher = expressionPatternByOperator.matcher(strippedInput);
            while (!rootExpressionFound && matcher.find()) {
                rootExpressionFound = true;
                if (operator.getType().isOperandNeededOnLeftSide()) {
                    String operandString = strippedInput.substring(0, matcher.start() - 1);
                    if ( isCorrectlyParenthesizedExpression(operandString)) {
                        Expression operandExpression = buildExpression(operandString);
                        operands.add(operandExpression);    
                    }
                    else {
                        rootExpressionFound = false;
                    }
                }
                if (rootExpressionFound && operator.getType().isOperandNeededOnRightSide()) {
                    String operandString = strippedInput.substring(matcher.end() + 1);
                    if ( isCorrectlyParenthesizedExpression(operandString)) {
                        Expression operandExpression = buildExpression(operandString);
                        operands.add(operandExpression);    
                    }
                    else {
                        rootExpressionFound = false;
                    }
                }
            }
        }
        Expression result = null;
        if (operator != null && rootExpressionFound) {
            isExpressionCorrect = true;
            result = new ArithmeticExpression(operator, operands);
        } else {
            try {
                Double value = Double.parseDouble(input);
                isExpressionCorrect = true;
                result = new Constant(value);
            } catch (NumberFormatException e) {
            }
        }
        if (isExpressionCorrect) {
            return result;
        } else {
            throw new IllegalArgumentException("Wrong input");
        }
    }

    public String stripInputStringExpression(String input) {
        String result = new String(input);
        while (result.matches(EXPRESSION_STRIP_REGEX)) {
            result = result.replaceAll(EXPRESSION_STRIP_REGEX, "$1");
        }
        return result;
    }

    private boolean isCorrectlyParenthesizedExpression(String input) {
        int openParenthesesCount = 0;
        Pattern parenthesesPattern = Pattern.compile(PARENTHESES_REGEX);
        Matcher matcher = parenthesesPattern.matcher(input);
        while (matcher.find() && openParenthesesCount >= 0) {
            String match = matcher.group();
            if (match.equals(OPENING_PARENTHESES)) {
                openParenthesesCount++;
            } else if (match.equals(CLOSING_PARENTHESES)) {
                openParenthesesCount--;
            }
        }
        boolean result = true;
        if (openParenthesesCount != 0) {
            result = false;
        }
        return result;
    }

}
