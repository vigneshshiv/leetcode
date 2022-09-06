package code.java.strings;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/rings-and-rods/
 */
public class RingsAndRods {

    private static Function<String, Integer> colorIndex = (color) -> switch (color) {
        case "R" -> 0;
        case "G" -> 1;
        case "B" -> 2;
        default -> -1;
    };

    private static int countPoints(String rings) {
        Map<Integer, Boolean[]> table = buildTable(rings);
        int count = 0;
        for (Boolean[] group : table.values()) {
            int idx = 0;
            for (Boolean color : group) {
                idx++;
                if (Objects.isNull(color) || !color) break;
            }
            count += idx == 3 ? 1 : 0;
        }
        return count;
    }

    private static Map<Integer, Boolean[]> buildTable(String rings) {
        Map<Integer, Boolean[]> table = new HashMap<>();
        for (int i = 0; i < rings.length(); i += 2) {
            int key = rings.charAt(i + 1) - '0';
            Boolean[] values = table.getOrDefault(key, new Boolean[3]);
            String color = String.valueOf(rings.charAt(i));
            int colorIdx = colorIndex.apply(color);
            values[colorIdx] = true;
            table.put(key, values);
        }
        return table;
    }

    /**
     * https://leetcode.com/problems/rings-and-rods/discuss/1624277
     */
    private static int countPointsOptimal(String rings) {
        int[] rods = new int[10];
        for (int i = 0; i < rings.length(); i += 2) {
            int idx = rings.charAt(i + 1) - '0';
            char c = rings.charAt(i);
            int color = c == 'R' ? 1 : c == 'G' ? 2 : 4;
            rods[idx] |= color;
        }
        return (int) Arrays.stream(rods).filter(v -> v == 7).count();
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (input, count) -> System.out.println("Input - " + input + ", Count - " + count);
        //
        String rings = "B0R0G0R9R0B0G0";
        int count = countPointsOptimal(rings);
        logger.accept(rings, count);
        //
        rings = "G4"; count = countPoints(rings);
        logger.accept(rings, count);
    }

}
