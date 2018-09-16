
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class test {

    @Test
    public void testN1() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h", "--si" ,"src/forTest/kojima.jpg");
        expected.add("39KB (src/forTest/kojima.jpg)");
        Du.main(res);
        assertEquals(expected, Du.result);
    }


    @Test
    public void testN2() {
        List<String> expected = new ArrayList<>();
        List<String> res = Collections.singletonList("src/forTest/kojima.jpg");
        Du.main(res);
        expected.add("40 (src/forTest/kojima.jpg)");
        assertEquals(expected, Du.result);
    }

    @Test
    public void testN3() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h", "-c", "src/forTest/example.txt" ,"src/forTest/kojima.jpg");
        expected.add("42KB");
        Du.main(res);
        assertEquals(expected, Du.result);
    }

    @Test
    public void testN4() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h","src/forTest");
        expected.add("42KB (src/forTest)");
        Du.main(res);
        assertEquals(expected, Du.result);
    }

    @Test
    public void testN5() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h", "-c","src/forTest", "src/forTest2/pictureForTest.jpg");
        expected.add("176KB");
        Du.main(res);
        assertEquals(expected, Du.result);
    }

    @Test
    public void testN6() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h", "-c", "src/forTest", "src/forTest2");
        expected.add("17MB");
        Du.main(res);
        assertEquals(expected, Du.result);
    }


}
