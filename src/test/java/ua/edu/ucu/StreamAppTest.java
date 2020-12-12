package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.Arrays;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }
    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testRestart() {
        System.out.println("testRestart");
        int expResult = 42;
        int res = intStream
                .filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)) // 0, 1, 2, 3, 4, 5, 8, 9, 10
                .sum(); // 42
        assertEquals(expResult, res);
    }

    @Test
    public void testEmptyMid() {
        System.out.println("empty mid method");
        IntStream stream = AsIntStream.of(1, 2, 3);
        int expResult = 0;
        long res = stream
                .filter(x -> x < 0) // empty
                .map(x -> x * x) // empty
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)) // empty
                .count(); // 0
        assertEquals(expResult, res);
    }

    @Test
    public void testCount() {
        System.out.println("sum");
        int expResult = 5;
        int result = Math.toIntExact(intStream.count());
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        System.out.println("count");
        int expSumResult = 5;
        int sumResult = intStream.sum();
        assertEquals(expSumResult, sumResult);
    }

    @Test
    public void testMin() {
        System.out.println("min");
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        System.out.println("max");
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testAvg() {
        double expResult = 1.0;
        Double result = intStream.average();
        assertEquals(expResult, result, 0.00001);
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        IntStream emptyStream = AsIntStream.of();
        emptyStream.min();
    }
    
}
