package code.java.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KthPermutation {

    private static int findKthPermutation(int n, int k) {
        List<Integer> permutation = new ArrayList<>();
        int[] nums = new int[n + 1];
        Arrays.setAll(nums, idx -> idx);
        List<Integer> elements = Arrays.stream(nums).mapToObj(num -> (Integer) num).collect(Collectors.toList());
        int[] fact = factorial(n);
        k -= 1;
        while (n > 0) {
            int segment = fact[n] / n;
            int idx = k / segment;
            permutation.add(elements.get(idx));
            elements.remove(idx);
            n -= 1;
            k %= segment;
        }
        return Integer.valueOf(permutation.stream().map(num -> String.valueOf(num)).collect(Collectors.joining()));
    }

    private static int[] factorial(int n) {
        int[] fact = new int[n + 1];
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = i * fact[i - 1];
        }
        return fact;
    }

    public static void main(String[] args) {
        int n = 4, k = 16;
        System.out.println("4 Digits, 16th Permutation of a Number - " + findKthPermutation(n, k));
    }

}
