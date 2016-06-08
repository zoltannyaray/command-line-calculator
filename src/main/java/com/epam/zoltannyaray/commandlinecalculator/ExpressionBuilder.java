package com.epam.zoltannyaray.commandlinecalculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionBuilder {

    private static final String STRIP_EXPRESSION_REGEX = "(^[\\(\\s]+|[\\s\\)]+$)";
    private static final String EXPRESSION_MATCH_LEFT_SIDE = "leftSide";
    private static final String EXPRESSION_MATCH_RIGHT_SIDE = "rightSide";
    
    OperatorPrecedenceProvider operatorPrecedenceProvider;
    
    public ExpressionBuilder(OperatorPrecedenceProvider operatorPrecedenceProvider) {
        super();
        this.operatorPrecedenceProvider = operatorPrecedenceProvider;
    }

    public Expression buildExpression( String input ) {
        String strippedInput = stripInputStringExpression(input);
        Iterator<Operator> operatorsInPrecedenceOrderLowestFirst = operatorPrecedenceProvider.getOperatorsInPrecedenceOrderLowestFirst().iterator();
        Pattern expressionPatternByOperator;
        List<Expression> operands = new ArrayList<Expression>();
        boolean rootExpressionFound = false;
        boolean isExpressionCorrect = false;
        Operator operator = null;
        while ( !rootExpressionFound && operatorsInPrecedenceOrderLowestFirst.hasNext() ) {
            operator = operatorsInPrecedenceOrderLowestFirst.next();
            expressionPatternByOperator = getExpressionPatternByOperator(operator);
            Matcher matcher = expressionPatternByOperator.matcher(strippedInput);
            if ( matcher.find() ) {
                rootExpressionFound = true;
                if ( operator.getType().isOperandNeededOnLeftSide() ) {
                    String operandString = matcher.group(EXPRESSION_MATCH_LEFT_SIDE);
                    Expression operandExpression = buildExpression(operandString);
                    operands.add(operandExpression);
                }
                if ( operator.getType().isOperandNeededOnRightSide() ) {
                    String operandString = matcher.group(EXPRESSION_MATCH_RIGHT_SIDE);
                    Expression operandExpression = buildExpression(operandString);
                    operands.add(operandExpression);
                }
            }
        }
        Expression result = null;
        if ( operator != null && rootExpressionFound ) {
            isExpressionCorrect = true;
            result = new ArithmeticExpression( operator, operands );
        }
        else {
            try {
                Double value = Double.parseDouble(input);
                isExpressionCorrect = true;
                result = new Literal(value);
            }
            catch ( NumberFormatException e ) {
            }
        }
        if ( isExpressionCorrect ) {
            return result;
        }
        else {
            throw new IllegalArgumentException( "Wrong input" );
        }
    }
    
    public String stripInputStringExpression( String input ) {
        return input.replaceAll(STRIP_EXPRESSION_REGEX, "");
    }
    
    public Pattern getExpressionPatternByOperator( Operator operator ) {
        String expressionRegex = "";
        if ( operator.getType().isOperandNeededOnLeftSide() ) {
            expressionRegex += "(?<" + EXPRESSION_MATCH_LEFT_SIDE + ">.+)";
        }
        expressionRegex += Pattern.quote(operator.getSign());
        if ( operator.getType().isOperandNeededOnRightSide() ) {
            expressionRegex += "(?<" + EXPRESSION_MATCH_RIGHT_SIDE + ">.+)";
        }
        return Pattern.compile(expressionRegex);
    }
    
}
