package code.java.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 */
public class StringsSwapEqual {

    /**
     * Seems char positions swaps can be made in any order, as it calculates only forward index, this solution will not work.
     *
     * Eg: s1 = jmpjg, s2 = pmjjg
     */
    private static boolean areAlmostEqualInitialTry(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int idx = 0;
        String store = null;
        boolean one_swap = false;
        while (idx < s1.length()) {
            if (s1.charAt(idx) != s2.charAt(idx)) {
                int pos = s2.indexOf(s1.charAt(idx), idx);
                if (pos == -1) return false;
                if (pos == s1.indexOf(s2.charAt(idx), idx) && !one_swap) {
                    store = s1.charAt(idx) + "#" + s2.charAt(idx);
                    one_swap = true;
                } else {
                    if (!store.equals(s1.charAt(idx) + "#" + s2.charAt(idx)) && !store.equals(s2.charAt(idx) + "#" + s1.charAt(idx))) {
                        return false;
                    }
                }
            }
            idx += 1;
        }
        return true;
    }

    private static boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                idx.add(i);
            }
            if (idx.size() > 2) return false;
        }
        return idx.isEmpty() || (idx.size() == 2
                && s1.charAt(idx.get(0)) == s2.charAt(idx.get(1)) && s1.charAt(idx.get(1)) == s2.charAt(idx.get(0)));
    }

    public static void main(String[] args) {
        BiConsumer<String[], Boolean> logger = (input, result) -> {
            System.out.println("S1 - " + input[0] + ", S2 - " + input[1] + ", One Swap ahead - " + result);
        };
        //
        String s1 = "bank", s2 = "kanb";
        boolean one_swap_ahead = areAlmostEqual(s1, s2);
        logger.accept(new String[] {s1, s2}, one_swap_ahead);
        //
        s1 = "attack"; s2 = "defend";
        one_swap_ahead = areAlmostEqual(s1, s2);
        logger.accept(new String[] {s1, s2}, one_swap_ahead);
        //
        s1 = "kelb"; s2 = "kelb";
        one_swap_ahead = areAlmostEqual(s1, s2);
        logger.accept(new String[] {s1, s2}, one_swap_ahead);
        //
        s1 = "jmpjg"; s2 = "pmjjg";
        one_swap_ahead = areAlmostEqual(s1, s2);
        logger.accept(new String[] {s1, s2}, one_swap_ahead);
    }

}
