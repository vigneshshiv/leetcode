package code.java.strings;

public class SkipCharAndString {

    /**
     * n - # of chars in str
     * s - # of chars added to string builder
     * Time complexity: O(n)
     * Space complexity: O(n + s), n is the recursive call stack and s is char added to the result
     */
    private static String skipCharacter(String str, char ch, StringBuilder result) {
        if (str.isEmpty()) return result.toString();
        if (!(str.charAt(0) == ch)) {
            result.append(str.charAt(0));
        }
        return skipCharacter(str.substring(1), ch, result);
    }

    /**
     * n - # of chars in str
     * s - # of chars added to string builder
     * Time complexity: O(n)
     * Space complexity: O(n + s), n is the recursive call stack and s is char added to the result
     */
    private static String skipCharacter(String str, char ch) {
        if (str.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        if (!(str.charAt(0) == ch)) {
            result.append(str.charAt(0));
        }
        result.append(skipCharacter(str.substring(1), ch));
        return result.toString();
    }

    /**
     * n - # of words in str
     * s - # of words added to string builder
     * Time complexity: O(n)
     * Space complexity: O(n + s), n is the recursive call stack and s is char added to the result
     */
    private static String skipString(String str, String word, StringBuilder result) {
        if (str.isEmpty()) return result.toString();
        if (str.startsWith(word)) {
            // +1 for space
            return skipString(str.substring(word.length() + 1), word, result);
        } else {
            result.append(str.charAt(0));
        }
        return skipString(str.substring(1), word, result);
    }

    public static void main(String[] args) {
        String str = "abccda";
        char ch = 'a';
        String result = skipCharacter(str, ch, new StringBuilder());
        System.out.println("Skip Char " + str + " removed char - " + ch + ", Result - " + result);
        //
        str = "abccda";
        ch = 'a';
        result = skipCharacter(str, ch);
        System.out.println("Skip Char 2nd Approach: " + str + " removed char - " + ch + ", Result - " + result);
        // Skip String
        str = "Apple IPhone is the largest mobile brand in the world, and I love Apple IPhone";
        String word = "Apple";
        result = skipString(str, word, new StringBuilder());
        System.out.println("Skip String: " + str + ", remove word - " + word + ", \nResult - " + result);
    }

}
