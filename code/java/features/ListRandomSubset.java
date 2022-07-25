package code.java.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ListRandomSubset {

    /**
     * Subset list
     */
    static List<Integer> getRandomSubset(List<Integer> list) {
        Random random = new Random();
        Predicate<Integer> decider = (value) -> random.nextBoolean();
        return list.stream().filter(decider).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 4, 5, 8, 1, 7, 6, 9);
        List<Integer> subsetList = getRandomSubset(list);
        System.out.println("Subset list - " + subsetList);
    }

}
