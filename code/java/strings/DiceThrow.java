package code.java.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DiceThrow {

    private static Supplier<List<String>> emptyList = () -> new ArrayList<>();

    private static List<String> diceThrow(String prefix, int target, int face) {
        List<String> result = emptyList.get();
        if (target == 0) {
            result.add(prefix);
            return result;
        }
        for (int i = 1; i <= face && i <= target; i++) {
            result.addAll(diceThrow(prefix + i, target - i, face));
        }
        return result;
    }

    public static void main(String[] args) {
        int face = 6;
        int target = 4;
        List<String> result = diceThrow("", target, face);
        System.out.println("Target - 4, Dice Result - " + result);
    }

}
