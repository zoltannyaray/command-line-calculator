package com.epam.zoltannyaray.commandlinecalculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionBuilder {

    private static final String REGEX_EXPRESSION_STRIP_GROUP_INSIDE_PARENTHESES = "insideParenthesesGroup";
    private static final String REGEX_EXPRESSION_STRIP = "^\\s*\\(\\s*(?<" + REGEX_EXPRESSION_STRIP_GROUP_INSIDE_PARENTHESES + ">.+?)\\s*\\)\\s*$";
    private static final String CHARACTER_OPENING_PARENTHESES = "(";
    private static final String CHARACTER_CLOSING_PARENTHESES = ")";
    private static final String REGEX_ONLY_PARENTHESES = "[\\" + CHARACTER_OPENING_PARENTHESES + "\\" + CHARACTER_CLOSING_PARENTHESES + "]";

    private OperatorPrecedenceProvider operatorPrecedenceProvider;

    public ExpressionBuilder(OperatorPrecedenceProvider operatorPrecedenceProvider) {
        this.operatorPrecedenceProvider = operatorPrecedenceProvider;
    }

    public Expression buildExpression(String input) {
        String strippedInput = stripInputStringExpression(input);
        Iterator<Operator> operatorsInPrecedenceOrderLowestFirst = operatorPrecedenceProvider.getOperatorsInPrecedenceOrderLowestFirst().iterator();
        Pattern expressionPatternByOperator;
        List<Expression> operands = new ArrayList<Expression>();
        boolean rootExpressionFound = false;
        Operator operator = null;
        while (!rootExpressionFound && operatorsInPrecedenceOrderLowestFirst.hasNext()) {
            operator = operatorsInPrecedenceOrderLowestFirst.next();
            expressionPatternByOperator = Pattern.compile(Pattern.quote(operator.getSign()));
            Matcher matcher = expressionPatternByOperator.matcher(strippedInput);
            while (!rootExpressionFound && matcher.find()) {
                rootExpressionFound = true;
                if (operator.getType().isOperandNeededOnLeftSide()) {
                    String operandString = strippedInput.substring(0, matcher.start());
                    if (operandString.length() > 0 && isCorrectlyParenthesizedExpression(operandString)) {
                        Expression operandExpression = buildExpression(operandString);
                        operands.add(operandExpression);
                    } else {
                        rootExpressionFound = false;
                    }
                }
                if (rootExpressionFound && operator.getType().isOperandNeededOnRightSide()) {
                    String operandString = strippedInput.substring(matcher.end());
                    if (operandString.length() > 0 && isCorrectlyParenthesizedExpression(operandString)) {
                        Expression operandExpression = buildExpression(operandString);
                        operands.add(operandExpression);
                    } else {
                        rootExpressionFound = false;
                    }
                }
            }
        }
        Expression result = null;
        boolean isExpressionCorrect = false;
        if (operator != null && rootExpressionFound) {
            isExpressionCorrect = true;
            result = new ArithmeticExpression(operator, operands);
        } else {
            try {
                Double value = Double.parseDouble(strippedInput);
                isExpressionCorrect = true;
                result = new Constant(value);
            } catch (NumberFormatException e) {
            }
        }
        if (isExpressionCorrect) {
            return result;
        } else {
            throw new InvalidExpressionException();
        }
    }

    public String stripInputStringExpression(String input) {
        String strippedInput = new String(input);
        Pattern pattern = Pattern.compile(REGEX_EXPRESSION_STRIP);
        Matcher matcher = pattern.matcher(strippedInput);
        while (matcher.find() && isCorrectlyParenthesizedExpression(matcher.group(REGEX_EXPRESSION_STRIP_GROUP_INSIDE_PARENTHESES))) {
            strippedInput = matcher.group(REGEX_EXPRESSION_STRIP_GROUP_INSIDE_PARENTHESES);
            matcher = pattern.matcher(strippedInput);
        }
        return strippedInput;
    }

    private boolean isCorrectlyParenthesizedExpression(String input) {
        int openParenthesesCount = 0;
        Pattern pattern = Pattern.compile(REGEX_ONLY_PARENTHESES);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find() && openParenthesesCount >= 0) {
            String match = matcher.group();
            if (match.equals(CHARACTER_OPENING_PARENTHESES)) {
                openParenthesesCount++;
            } else if (match.equals(CHARACTER_CLOSING_PARENTHESES)) {
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
