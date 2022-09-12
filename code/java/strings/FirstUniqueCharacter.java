package code.java.strings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqueCharacter {

    private static int firstUniqCharInitialTry(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            int idx = s.indexOf(s.charAt(i), i + 1);
            if (idx == -1) return i;
        }
        return -1;
    }

    private static int firstUniqChar(String s) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for (int idx = 0; idx < s.length(); idx++) {
            if (chars[s.charAt(idx) - 'a'] == 1) return idx;
        }
        return -1;
    }

    /**
     * TODO:
     */
    private static int firstUniqCharByQueue(String s) {
        Deque<Character> queue = new ArrayDeque();
        for (char c : s.toCharArray()) {
            if (!queue.isEmpty() && queue.peekFirst() == c) {
                queue.poll();
            } else {
                queue.push(c);
            }
        }
        int idx = !queue.isEmpty() ? s.indexOf(queue.poll()) : -1;
        return idx > 0 ? idx - 1 : idx;
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> logger = (str, index) -> System.out.println("Str - " + str + ", index - " + index);
        //
        String str = "leetcode";
        int idx = firstUniqChar(str);
        logger.accept(str, idx);
        //
        str = "loveleetcode";
        idx = firstUniqCharByQueue(str);
        logger.accept(str, idx);
        //
        str = "aabb";
        idx = firstUniqChar(str);
        logger.accept(str, idx);
        //
        str = "z";
        idx = firstUniqCharByQueue(str);
        logger.accept(str, idx);
    }

}
