/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.bracket.formatter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

        return formalize(values, operations, trimmed.indexOf('=') != -1);
    }

    private String formalize(List<String> values, List<String> operations, boolean hasEqual)
    {
        StringBuilder formalize = new StringBuilder();

        int size = hasEqual ? values.size() - 1 : values.size();
        for(int index = 0; index < size; index++)
        {
            String value = values.get(index);

            if(value.contains(")*") || value.contains(")/"))
            {
                formalize.insert(insertAt(formalize), "(").append(value).append(")");
            }
            else if(value.contains("*") || value.contains("/"))
            {
                formalize.append("(").append(value).append(")");
            }
            else
            {
                formalize.append(value);
            }
            if(operations.size() > index) formalize.append(operations.get(index));
        }

        return formalize.toString();
    }

    private int insertAt(StringBuilder formalized)
    {
        for(int index = formalized.length() - 1 ; index > 0; index--)
        {
            if(Objects.equals(formalized.charAt(index), '('))
            {
                return index;
            }
        }
        return 0;
    }
}
