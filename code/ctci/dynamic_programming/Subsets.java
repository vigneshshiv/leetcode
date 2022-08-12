package code.ctci.dynamic_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Subsets {

    private static Supplier<List<Integer>> emptyList = () -> new ArrayList<>();
    private static Supplier<List<List<Integer>>> empty2DList = () -> {
        List<List<Integer>> sets = new ArrayList<>();
        sets.add(emptyList.get());
        return sets;
    };

    private static BiFunction<List<List<Integer>>, Integer, List<List<Integer>>> combiner = (subsets, item) -> {
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(subsets.stream().map(ArrayList::new).collect(Collectors.toList()));
        result.forEach(set -> set.add(0, item));
        return result;
    };

    private static List<List<Integer>> getSubsets(int[] numbers, int index) {
        List<List<Integer>> result = null;
        if (numbers.length == index) {
            result = empty2DList.get();
        } else {
            result = getSubsets(numbers, index + 1);
            result.addAll(combiner.apply(result, numbers[index]));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        List<List<Integer>> result = getSubsets(numbers, 0);
        System.out.println(result.toString());
    }

}
