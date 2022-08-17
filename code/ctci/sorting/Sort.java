package code.ctci.sorting;

import java.util.Arrays;
import java.util.function.BiConsumer;

public interface Sort {

    BiConsumer<String, String> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);

    /**
     * Swap 2 elements
     */
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
