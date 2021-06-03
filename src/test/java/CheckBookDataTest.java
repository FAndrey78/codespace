import com.alpha.controller.validation.CheckBookData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckBookDataTest {

    @Parameterized.Parameter
    public String value;

    @Parameterized.Parameter(1)
    public boolean expected1;

    @Parameterized.Parameter(2)
    public boolean expected2;

    @Parameterized.Parameter(3)
    public boolean expected3;

    @Parameterized.Parameter(4)
    public boolean expected4;

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {"1900", true, true, true, true},
                {"58", false, true, true, true},
                {"5678", false, true, true, true},
                {"1678.38", false, false, true, false},
                {"-68.25", false, false, false, false},
                {"-25", false, false, false, true},
                {"1000", true, true, true, true},
                {"2022", false, true, true, true},
                {"-2020", false, false, false, true},
                {"slfjs234dfg", false, false, false, false},
                {"2010", true, true, true, true}
        });
    }

    @Test
    public void testIsYear() {
        boolean result = CheckBookData.isYear(value);
        Assert.assertEquals(expected1,result);
        System.out.println(value + " >> " + expected1);
    }

    @Test
    public void testIsPages() {
        boolean result = CheckBookData.isPages(value);
        Assert.assertEquals(expected2,result);
    }

    @Test
    public void testIsCost() {
        boolean result = CheckBookData.isCost(value);
        Assert.assertEquals(expected3,result);
    }

    @Test
    public void testIsInt() {
        boolean result = CheckBookData.isInt(value);
        Assert.assertEquals(expected4,result);
    }
}
