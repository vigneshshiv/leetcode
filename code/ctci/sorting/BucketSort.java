package code.ctci.sorting;

import java.util.*;
import java.util.stream.IntStream;

public class BucketSort {

    private static Comparator<Integer> comparator = Comparator.naturalOrder();

    /**
     * Time complexity: O(n), where n is the number of elements in the array
     * Space complexity: O(n)
     */
    private static List<Integer> bucketSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return List.of();
        }
        if (arr.length == 1) {
            return List.of(arr[0]);
        }
        final int max = Arrays.stream(arr).max().getAsInt();
        final int bucketSize = (int) Math.sqrt(arr.length);
        // Create initial empty buckets
        List<List<Integer>> buckets = new ArrayList<>();
        IntStream.range(0, bucketSize).forEach(idx -> buckets.add(new ArrayList<>()));
        // Distribute array elements into their appropriate buckets by using hash index
        for (int num : arr) {
            buckets.get(hash(num, max, bucketSize)).add(num);
        }
        // Sort each bucket
        buckets.stream().forEach(bucket -> bucket.sort(comparator));
        // Concatenate the sorted buckets together to recreate the list
        List<Integer> results = new LinkedList<>();
        buckets.stream().forEach(bucket -> results.addAll(bucket));
        return results;
    }

    private static int hash(int value, int max, int bucketSize) {
        return (int) ((double) value / max * (bucketSize - 1));
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int[] input = arr.clone();
        List<Integer> results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] {1, 2, 3, 4, 5};
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] {1};
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] {5, 7, -2, 0, -3};
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] {};
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] { 20, 91, -6, 16, 0, 7, 51, 42, 3, 1 };
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] {2, 4, 5, 8, 1, 3};
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
        //
        arr = new int[] {80, 50, 60, 30, 20, 10, 70, 0, 40, 500, 600, 602, 200, 15};
        input = arr.clone();
        results = bucketSort(arr);
        Sort.logger.accept(Arrays.toString(input), results.toString());
    }

}
