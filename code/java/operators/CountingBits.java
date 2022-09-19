package code.java.operators;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class CountingBits {

    private static int[] countBits(int n) {
        int[] result = new int[n + 1];
        int offset = 1;
        for (int i = 1; i < n + 1; i++) {
            if (i == offset * 2) {
                offset *= 2;
            }
            result[i] = result[i - offset] + 1;
        }
        return result;
    }

    private static int[] countBitsOptimal(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<Integer, int[]> logger = (N, result) -> System.out.println("N - " + N + ", Result - " + Arrays.toString(result));
        //
        int N = 2;
        int[] result = countBits(N);
        logger.accept(N, result);
        result = countBitsOptimal(N);
        logger.accept(N, result);
        //
        N = 5;
        result = countBits(N);
        logger.accept(N, result);
        result = countBitsOptimal(N);
        logger.accept(N, result);
        //
        N = 8;
        result = countBits(N);
        logger.accept(N, result);
        result = countBitsOptimal(N);
        logger.accept(N, result);
    }

}
