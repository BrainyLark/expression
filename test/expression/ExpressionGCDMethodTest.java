/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author archer
 */
@RunWith(Parameterized.class)
public class ExpressionGCDMethodTest {
    private final String input;
    private final String expected;
    private Expression expression;

    public ExpressionGCDMethodTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }   

    @Before
    public void initialize() {
        expression = new Expression();
    }
    
    @Parameterized.Parameters
    public static Collection testArguments() {
        return Arrays.asList(new Object[][] {
            {"", "arg empty"},
            {"0/0", "zero"},
            {"7777777888888888999999999000000/99999999999999999999944411111", "not integer"},
            {"-3424999999499494994949499494949949/-999999922292992929911132313231312", "not integer"},
            {"this is the text", "not integer"},
            {"this is/the text", "not integer"},
            {"qwerty/?123**fsdf", "not integer"},
            {"12/2", "6/1"},
            {"12/24", "1/2"},
            {"1200/12", "100/1"},
            {"410/4100", "1/10"},
            {"24/684", "2/57"},
            {"5/150035", "1/30007"},
            {"18/9999999", "2/1111111"},
            {"6/987654", "1/164609"},
            {"1/1", "1/1"}
        });
    }
    
    @Test
    public void testExpressionGCDMethod() {
        System.out.println("input: " + input);
        assertEquals(expected, expression.gcd(input));
    }

}
