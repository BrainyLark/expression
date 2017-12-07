package expression;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ExpressionAddMethodTest {

    private final String firstInput;
    private final String secondInput;
    private final String expectedResult;
    private Expression expression;

    @Before
    public void initialize() {
        expression = new Expression();
    }

    public ExpressionAddMethodTest(String firstInput, String secondInput, String expectedResult) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection addableArguments() {
        return Arrays.asList(new Object[][]{
            {"", "", "p1 empty"},
            {"", "0/0", "p1 empty"},
            {"0/0", "", "p2 empty"},
            {"10000000000000/100000000000000", "3333333333333/999999999999999999", "not integer"},
            {"-10000000000000/-100000000000000", "3333333333333/-999999999999999999", "not integer"},
            {"word/bla", "0/0", "not integer"},
            {"qwerty/?123**fsdf", "0/0", "not integer"},
            {"0/0", "0/0", "0/0"},
            {"0/0", "1/3", "0/0"},
            {"1/3", "32/3", "99/9"},
            {"1/0", "132/3", "3/0"},
            {"1/3", "233/0", "699/0"},
            {"2/2", "334/3", "674/6"},
            {"1/200", "2432/3", "486403/600"},
            {"1/3005", "265/3", "796328/9015"},
            {"1/12345", "234/0", "2888730/0"},
            {"2/42334", "3453/3", "146179308/127002"},
            {"1/9090", "2/3", "18183/27270"},
            {"1/10", "253/3", "2533/30"},
            {"1/20", "255/0", "5100/0"},
            {"2/245", "35/3", "8581/735"},
            {"1/765", "25/3", "19128/2295"},
            {"1/90", "29/3", "2613/270"},
            {"1/12", "21/0", "252/0"},
            {"2/121", "32/3", "3878/363"}
        });
    }

    @Test
    public void testExpressionAddMethodTest() {
        System.out.println("arg1: " + firstInput + ", arg2: " + secondInput);
        assertEquals(expectedResult, expression.add(firstInput, secondInput));
    }

}
