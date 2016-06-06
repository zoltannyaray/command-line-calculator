package com.epam.zoltannyaray.commandlinecalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStringTokenizer implements StringTokenizer {

    private String regex;

    public RegexStringTokenizer(String regex) {
        super();
        this.regex = regex;
    }

    public List<String> tokenize(String input) {
        if ( input == null ) {
            throw new IllegalArgumentException("Input should not be null");
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> tokens = new ArrayList<String>();
        while (matcher.find()) {
            tokens.add(matcher.group(1));
        }
        return tokens;
    }

}
