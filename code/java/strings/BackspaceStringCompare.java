package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {

    private static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        while (i >= 0 || j >= 0) {
            int shift = 0;
            while (i >= 0 && (shift > 0 || s.charAt(i) == '#')) {
                shift += s.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            shift = 0;
            while (j >= 0 && (shift > 0 || t.charAt(j) == '#')) {
                shift += t.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            if (i >= 0 && j >= 0 && s.charAt(i) ==  t.charAt(j)) {
                i -= 1; j -= 1;
            } else {
                break;
            }
        }
        return i == -1 && j == -1;
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (str, result) -> {
            System.out.println("S - " + str[0] + ", T - " + str[1] + ", Result - " + result);
        };
        //
        String s = "ab#c", t = "ad#c";
        boolean result = backspaceCompare(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "ab##"; t = "c#d#";
        result = backspaceCompare(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "a#c"; t = "b";
        result = backspaceCompare(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "a##c"; t = "#a#c";
        result = backspaceCompare(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "bxj##tw"; t = "bxo#j##tw";
        result = backspaceCompare(s, t);
        logger.accept(new String[] {s, t}, result);
        //
        s = "bxj##tw"; t = "bxj###tw";
        result = backspaceCompare(s, t);
        logger.accept(new String[] {s, t}, result);
    }

}
