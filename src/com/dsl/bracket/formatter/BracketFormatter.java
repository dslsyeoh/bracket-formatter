/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.bracket.formatter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BracketFormatter implements Formatter
{
    private static final String EXCLUDE_REGEX = "[-+=]";
    private static final String FILTER_REGEX = "[^-+]";

    @Override
    public String formalizeToBracket(String input)
    {
        String trimmed = Arrays.stream(input.split("")).filter(s -> !s.matches("[\\s]")).collect(Collectors.joining());
        List<String> values = Arrays.stream(trimmed.split(EXCLUDE_REGEX)).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        List<String> operations = Arrays.stream(trimmed.split(FILTER_REGEX)).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        StringBuilder formalized = new StringBuilder();

        int size = trimmed.indexOf('=') != -1 ? values.size() - 1 : values.size();
        for(int index = 0; index < size; index++)
        {
            String value = values.get(index);
            if(value.contains("*") || value.contains("/"))
            {
                formalized.append("(").append(value).append(")");
            }
            else
            {
                formalized.append(value);
            }
            if(operations.size() > index)
            {
                formalized.append(operations.get(index));
            }
        }

        return formalized.toString();
    }
}
