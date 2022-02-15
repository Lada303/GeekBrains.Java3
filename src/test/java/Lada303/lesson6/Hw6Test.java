package Lada303.lesson6;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Hw6Test {
    private final Hw6 hw6 = new Hw6();

    //test for hw6.arrayAfterLast4(int[] arr)
    @ParameterizedTest
    @MethodSource("dataForArrayAfterLast4")
    public void testArrayAfterLast4(int[] resultArr, int[] inputArr) {
         Assertions.assertArrayEquals(resultArr, hw6.arrayAfterLast4(inputArr));
    }

    public static Stream<Arguments> dataForArrayAfterLast4() {
        List<Arguments> dataList = new ArrayList<>();
        dataList.add(Arguments.arguments(new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
        dataList.add(Arguments.arguments(new int[]{1, 7, 5, 3, 2}, new int[]{1, 2, 4, 1, 7, 5, 3, 2}));
        dataList.add(Arguments.arguments(new int[]{}, new int[]{1, 2, 4}));
        return dataList.stream();
    }

    @Test
    public void testExcArrayAfterLast4 () {
        Assertions.assertThrows(RuntimeException.class, () -> hw6.arrayAfterLast4(new int[]{1, 2, 3, 1, 7}));
    }

    //rest for hw6.isArrContainsOnly1And4(int[] arr)
    @ParameterizedTest
    @MethodSource("dataForIsArrContainsOnly1And4")
    public void testIsArrContainsOnly1And4(boolean resultArr, int[] inputArr) {
        Assertions. assertEquals(resultArr, hw6.isArrContainsOnly1And4(inputArr));
    }
    public static Stream<Arguments> dataForIsArrContainsOnly1And4() {
        List<Arguments> dataList = new ArrayList<>();
        dataList.add(Arguments.arguments(true, new int[]{1, 1, 4, 4, 1, 1, 4, 1, 4}));
        dataList.add(Arguments.arguments(false, new int[]{1, 1, 1, 1, 1}));
        dataList.add(Arguments.arguments(false, new int[]{4, 4, 4}));
        dataList.add(Arguments.arguments(false, new int[]{4, 4, 2}));
        dataList.add(Arguments.arguments(false, new int[]{1, 2, 3, 4}));
        dataList.add(Arguments.arguments(false, new int[]{11, 44, 14, 41}));
        return dataList.stream();
    }


}
