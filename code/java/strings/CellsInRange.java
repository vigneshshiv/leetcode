package code.java.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/
 */
public class CellsInRange {

    private static List<String> cellsInRange(String s) {
        List<String> result = new ArrayList<>();
        int colStart = s.charAt(0) - 'A', colEnd = s.charAt(3) - 'A';
        for ( ; colStart <= colEnd; colStart++) {
            int rowStart = s.charAt(1) - '0', rowEnd = s.charAt(4) - '0';
            while (rowStart <= rowEnd) {
                result.add(String.valueOf((char) ('A' + colStart) + String.valueOf(rowStart)));
                rowStart += 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BiConsumer<String, List<String>> logger = (input, result) -> System.out.println("Input - " + input + ", Result - " + result);
        //
        String str = "K1:L2";
        List<String> result = cellsInRange(str);
        logger.accept(str, result);
        //
        str = "A1:F1";
        result = cellsInRange(str);
        logger.accept(str, result);
    }

}
