package code.java.strings;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
public class GreatestCommonDivisorOfStrings {
    
    public static String gcdOfStrings(String str1, String str2) {
        int str1Len = str1.length(), str2Len = str2.length();
        if (str1.charAt(0) != str2.charAt(0)) {
            return "";
        }
        Function<Integer, Boolean> isDivisor = len -> {
            if (str1Len % len != 0 || str2Len % len != 0) {
                return false;
            }
            int f1 = str1Len / len, f2 = str2Len / len;
            String mini = str2.substring(0, len);
            return Objects.equals(mini.repeat(f1), str1) && Objects.equals(mini.repeat(f2), str2);
        };
        for (int i = Math.min(str1Len, str2Len); i > 0; i--) {
            if (isDivisor.apply(i)) {
                return str1.substring(0, i);
            }
        }
        return "";
    }

    /**
     * Not working for edge cases
     */
    public static String gcdOfStringsTrial(String str1, String str2) {
        int str1Len = str1.length(), str2Len = str2.length();
        if (str1.charAt(0) != str2.charAt(0)) {
            return "";
        }
        LinkedList<Character> divisor = new LinkedList<>();
        for (int i = 0; i < Math.min(str1Len, str2Len); i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                if (Objects.nonNull(divisor.peekFirst()) && str1.charAt(i) == divisor.peekFirst()) {
                    break;
                }
                divisor.add(str2.charAt(i));
            } else if (!divisor.isEmpty()) {
                return "";
            }
        }
        return divisor.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (input, result) -> {
            System.out.println("Str1 - " + input[0] + ", Str2 - " + input[1] + ", Result - " + result);
        };
        //
        String str1 = "ABCABC", str2 = "ABC";
        String result = gcdOfStrings(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        result = gcdOfStringsTrial(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        //
        str1 = "ABABAB"; str2 = "ABAB";
        result = gcdOfStrings(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        result = gcdOfStringsTrial(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        //
        str1 = "LEET"; str2 = "CODE";
        result = gcdOfStrings(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        result = gcdOfStringsTrial(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        //
        str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX"; str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        result = gcdOfStrings(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
        result = gcdOfStringsTrial(str1, str2);
        logger.accept(new String[] {str1, str2}, result);
    }

}
