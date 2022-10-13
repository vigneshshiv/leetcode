package code.java.strings;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagrams {

    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = new char[26];
            // count each char occurrences
            for (char c : s.toCharArray()) chars[c - 'a']++;
            String key = String.valueOf(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            // build hashmap with values
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        BiConsumer<String[], List<List<String>>> logger = (strs, result) -> {
            System.out.println("Input - " + Arrays.toString(strs) + ", Result - " + result);
        };
        //
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        logger.accept(strs, result);
        //
        strs = new String[] {""};
        result = groupAnagrams(strs);
        logger.accept(strs, result);
        //
        strs = new String[] {"a"};
        result = groupAnagrams(strs);
        logger.accept(strs, result);
    }

}
