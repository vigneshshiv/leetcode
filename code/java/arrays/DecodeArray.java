package code.java.arrays;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/decode-xored-array/
 */
public class DecodeArray {

    private static int[] decode(int[] encoded, int first) {
        int[] result = new int[encoded.length + 1];
        result[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            result[i + 1] = result[i] ^ encoded[i];
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<int[], int[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Decoded Array - " + Arrays.toString(result));
        };
        int[] encoded = {1, 2, 3};
        int first = 1;
        int[] result = decode(encoded, first);
        logger.accept(encoded, result);
        //
        encoded = new int[] {6, 2, 7, 3};
        first = 4;
        result = decode(encoded, first);
        logger.accept(encoded, result);
    }

}
