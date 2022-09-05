package code.java.strings;

/**
 * https://leetcode.com/problems/decode-the-message/
 */
public class DecodeMessage {

    private static String decodeMessage(String key, String message) {
        char[] order = new char[26];
        int[] has = new int[26];
        char[] cipher = new char[26];
        int ptr = 0;
        //
        for (char c : key.toCharArray()) {
            if (c == ' ') continue;
            if (has[c - 'a'] == 0) {
                has[c - 'a'] = 1;
                order[c - 'a'] = (char) (ptr++ + 'a');
            }
        }
        for (char c : message.toCharArray()) {
            if (c != ' ') cipher[c - 'a'] = order[c - 'a'];
        }
        return new String(cipher);
    }

    public static void main(String[] args) {
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";
        String result = decodeMessage(key, message);
        System.out.println("Key - " + key + ", Message - " + message);
        System.out.println("Result - " + result);
        //
        key = "eljuxhpwnyrdgtqkviszcfmabo";
        message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb";
        result = decodeMessage(key, message);
        System.out.println("Key - " + key + ", Message - " + message);
        System.out.println("Result - " + result);
    }

}
