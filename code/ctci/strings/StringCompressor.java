package code.ctci.strings;

public class StringCompressor {

    /**
     * Implement a method to perform basic string compression using the counts
     * of repeated characters. For example, the string aabcccccaaa would become a2blc5a3, If the
     * "compressed" string would not become smaller than the original string, your method should return
     * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
     *
     * Time Complexity - O(s + k) time
     * Space Complexity - O(s + k) space
     */
    static void compress(String str) {
        int consecutiveCount = 0;
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            consecutiveCount++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i)).append(consecutiveCount);
                consecutiveCount = 0;
            }
        }
        String result = compressed.toString().length() < str.length() ? compressed.toString() : str;
        System.out.println("Given String - " + str + ", Compressed version - " + compressed.toString());
    }

    public static void main(String[] args) {
        compress("aabcccccaaa");
    }

}
