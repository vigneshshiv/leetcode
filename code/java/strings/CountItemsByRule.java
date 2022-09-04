package code.java.strings;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/count-items-matching-a-rule/
 */
public class CountItemsByRule {

    private static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        Function<String, Integer> ruleIndex = rule -> switch (rule) {
            case "type" -> 0;
            case "color" -> 1;
            case "name" -> 2;
            default -> -1;
        };
        int idx = ruleIndex.apply(ruleKey);
        Predicate<List<String>> filter = ruleItem -> Objects.equals(ruleItem.get(idx), ruleValue);
        return (int) items.stream().filter(filter).count();
    }

    public static void main(String[] args) {
        List<List<String>> items = Arrays.asList(
                Arrays.asList("phone", "blue", "pixel"),
                Arrays.asList("computer", "silver", "lenovo"),
                Arrays.asList("phone", "gold", "iphone")
        );
        String ruleKey = "color";
        String ruleValue = "silver";
        int result = countMatches(items, ruleKey, ruleValue);
        System.out.println(result);
        //
        items = Arrays.asList(
                Arrays.asList("phone", "blue", "pixel"),
                Arrays.asList("computer", "silver", "phone"),
                Arrays.asList("phone", "gold", "iphone")
        );
        ruleKey = "type";
        ruleValue = "phone";
        result = countMatches(items, ruleKey, ruleValue);
        System.out.println(result);
    }

}
