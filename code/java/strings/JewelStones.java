package code.java.strings;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/jewels-and-stones/
 */
public class JewelStones {

    private static int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelStore = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            jewelStore.add(c);
        }
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewelStore.contains(stones.charAt(i))) {
                count += 1;
            }
        }
        return count;
    }

    private static int numJewelsInStonesTwoLine(String jewels, String stones) {
        Set<Character> jewelStore = jewels.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        return (int) stones.chars().filter(c -> jewelStore.contains((char) c)).count();
    }

    private static int numJewelsInStonesOneLine(String jewels, String stones) {
        return (int) stones.chars().filter(c -> jewels.contains(String.valueOf((char) c))).count();
    }

    private static int numJewelsInStonesOneLineEasy(String jewels, String stones) {
        return stones.replaceAll("[^" + jewels + "]", "").length();
    }

    public static void main(String[] args) {
        String jewels = "aA", stones = "aAAbbbb";
        int count = numJewelsInStonesOneLineEasy(jewels, stones);
        System.out.println(count);
        //
        jewels = "z"; stones = "ZZ";
        count = numJewelsInStonesOneLineEasy(jewels, stones);
        System.out.println(count);
    }

}
