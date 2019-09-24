/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.bracket.formatter;

import com.dsl.bracket.formatter.BracketFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class BracketFormatterTest
{
    private BracketFormatter bracketFormatter;

    @BeforeAll
    public void setup()
    {
        bracketFormatter = new BracketFormatter();
    }

    @Test
    public void testFormalizeToBracket_1()
    {
        Assertions.assertEquals("1+2-3+(1+3)", bracketFormatter.formalizeToBracket("1 + 2 - 3 + (1 + 3) == 6"));
    }

    @Test
    public void testFormalizeToBracket_2()
    {
        Assertions.assertEquals("(3*2)+(1+2)", bracketFormatter.formalizeToBracket("3 * 2 + (1 + 2) == 6"));
    }

    @Test
    public void testFormalizeToBracket_3()
    {
        Assertions.assertEquals( "(3*2)+1+2", bracketFormatter.formalizeToBracket("3 * 2 + 1 + 2 == 6"));
    }

    @Test
    public void testFormalizeToBracket_4()
    {
        Assertions.assertEquals( "1+2+(2*3)", bracketFormatter.formalizeToBracket("1 + 2 + 2 * 3 == 6"));
    }

    @Test
    public void testFormalizeToBracket_5()
    {
        Assertions.assertEquals( "(2*3)+(2*3)", bracketFormatter.formalizeToBracket("2 * 3 + 2 * 3 == 6"));
    }

    @Test
    public void testFormalizeToBracket_6()
    {
        Assertions.assertEquals( "1+1+3+(2*3)+(2*3)", bracketFormatter.formalizeToBracket("1 + 1 + 3 + 2 * 3 + 2 * 3 == 6"));
    }

    @Test
    public void testFormalizeToBracket_7()
    {
        Assertions.assertEquals("(2*3)+(2*3)+(2*3)+(2*3)+(2*3)+(2*3)", bracketFormatter.formalizeToBracket("2 * 3 + 2 * 3 + 2 * 3  + 2 * 3 + 2 * 3 + 2 * 3 == 6"));
    }

    @Test
    public void testFormalizeToBracket_8()
    {
        Assertions.assertEquals( "(1/1)+3+(2*3)+(2*3)", bracketFormatter.formalizeToBracket("1 / 1 + 3 + 2 * 3 + 2 * 3 == 6"));
    }

    @Test
    public void testFormalizeToBracket_9()
    {
        Assertions.assertEquals("(1/2/3/4*5*6)+7+8+9", bracketFormatter.formalizeToBracket("1 / 2 / 3 / 4 * 5 * 6 + 7 + 8 + 9 == 6"));
    }

    @Test
    public void testFormalizeToBracket_10()
    {
        Assertions.assertEquals("1-2-(3*4)+(5/6)-(7*8)+9", bracketFormatter.formalizeToBracket("1 - 2 - 3 * 4 + 5 / 6 - 7 * 8 + 9 == 6"));
    }

    @Test
    public void testFormalizeToBracket_11()
    {
        Assertions.assertEquals("(3*4/5)+5", bracketFormatter.formalizeToBracket("3 * 4 / 5 + 5 = 6"));
    }

    @Test
    public void testFormalizeToBracket_12()
    {
        Assertions.assertEquals("(3*4/5/6)+5", bracketFormatter.formalizeToBracket("3 * 4 / 5 / 6 + 5 = 6"));
    }

    @Test
    public void testFormalizeToBracket_13()
    {
        Assertions.assertEquals("(1/2/3/4*5*6)+7+(8*9)", bracketFormatter.formalizeToBracket("1 / 2 / 3 / 4 * 5 * 6 + 7 + 8 * 9"));
    }

    @Test
    public void testFormalizeToBracket_14()
    {
        Assertions.assertEquals("((1+2+3+4+5+6)*8)", bracketFormatter.formalizeToBracket("(1 + 2 + 3 + 4 + 5 + 6) * 8"));
    }

    @Test
    public void testFormalizeToBracket_15()
    {
        Assertions.assertEquals("((1+2+3+4+5+6)*8*9)", bracketFormatter.formalizeToBracket("(1 + 2 + 3 + 4 + 5 + 6) * 8 * 9"));
    }

    @Test
    public void testFormalizeToBracket_16()
    {
        Assertions.assertEquals("((1+2+3+4+5+6)*8*9)+1+(2*3)", bracketFormatter.formalizeToBracket("(1 + 2 + 3 + 4 + 5 + 6) * 8 * 9 + 1 + 2 * 3"));
    }

    @Test
    public void testFormalizeToBracket_17()
    {
        Assertions.assertEquals("((1+2+3+4+5+6)*8*9)+(1/2*3)", bracketFormatter.formalizeToBracket("(1 + 2 + 3 + 4 + 5 + 6) * 8 * 9 + 1 / 2 * 3"));
    }

    @Test
    public void testFormalizeToBracket_18()
    {
        Assertions.assertEquals("((1+2+3+4+5+6)*8*9)+(1/2*3)+10+(2*3*4/5)", bracketFormatter.formalizeToBracket("(1 + 2 + 3 + 4 + 5 + 6) * 8 * 9 + 1 / 2 * 3 + 10 + 2 * 3 * 4 / 5"));
    }

}