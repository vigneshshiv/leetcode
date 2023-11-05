package code.java.strings;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/string-compression/
 */
public class StringCompression {
    
    /**
     * Partial Test case pass
     */
    public static int compress(char[] chars) {
        Map<Character, Long> frequency = IntStream.range(0, chars.length)
            .mapToObj(i -> chars[i])
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int idx = 0, count = 0;
        for (Map.Entry<Character, Long> entry : frequency.entrySet()) {
            chars[idx++] = entry.getKey();
            count += 1;
            if (entry.getValue() > 9) {
                chars[idx++] = (char) ((entry.getValue() / 10) + '0');
                chars[idx++] = (char) ((entry.getValue() % 10) + '0');
                count += 2;
            } else if (entry.getValue() > 1) {
                chars[idx++] = (char) (entry.getValue() + '0');
                count += 1;
            }
        }
        return count;
    }

    public static int compressOptimal(char[] chars) {
        int i = 0, res = 0; 
        while (i < chars.length) {
            int groupLength = 1;
            while (i + groupLength < chars.length && chars[i + groupLength] == chars[i]) {
                groupLength += 1;
            }
            chars[res++] = chars[i];
            if (groupLength > 1) {
                for (char c : Integer.toString(groupLength).toCharArray()) {
                    chars[res++] = c;
                }
            }
            i += groupLength;
        }
        return res;
    }

    public static void main(String[] args) {
        BiConsumer<char[], Object[]> logger = (input, result) -> {
            System.out.println("Input - " + Arrays.toString(input) + ", Modified Array - " + Arrays.toString((char[]) result[0]) + ", Count - " + (int) result[1]);
        };
        //
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'}, input = chars.clone();
        // int count = compress(chars);
        // logger.accept(input, new Object[] {chars, count});
        int count = compressOptimal(chars);
        logger.accept(input, new Object[] {chars, count});
        //
        chars = new char[] {'a'}; input = chars.clone();
        count = compress(chars);
        logger.accept(input, new Object[] {chars, count});
        //
        chars = new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}; input = chars.clone();
        count = compress(chars);
        logger.accept(input, new Object[] {chars, count});
        //
        chars = new char[] {'a', 'a', 'a', 'b', 'b', 'a', 'a'}; input = chars.clone();
        count = compressOptimal(chars);
        logger.accept(input, new Object[] {chars, count});
    }

}
