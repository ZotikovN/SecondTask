
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class test {

    @Test
    public void testN1() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h", "--si" ,"src/forTest/kojima.jpg");
        expected.add("src/forTest/kojima.jpg 39KB");
        MainLauncher.main(res);
        assertEquals(expected, MainLauncher.result);
    }


    @Test
    public void testN2() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("src/forTest/example.txt");
        expected.add("src/forTest/example.txt 2");
        MainLauncher.main(res);
        assertEquals(expected, MainLauncher.result);
    }

    @Test
    public void testN3() {
        List<String> expected = new ArrayList<>();
        List<String> res = Arrays.asList("-h", "-c", "src/forTest/example.txt" ,"src/forTest/kojima.jpg");
        expected.add("Размер каталога 42KB");
        MainLauncher.main(res);
        assertEquals(expected, MainLauncher.result);
    }
}
