package code.java.strings;

import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/decode-the-message/
 */
public class DecodeMessage {

    private static String decodeMessage(String key, String message) {
        char[] keyStore = new char[26];
        boolean[] visited = new boolean[26];
        StringBuilder builder = new StringBuilder();
        int ptr = 0;
        for (char c : key.toCharArray()) {
            if (c == ' ') continue;
            int idx = c - 'a';
            if (!visited[idx]) {
                visited[idx] = true;
                keyStore[idx] = (char) (ptr++ + 'a');
            }
        }
        for (char c : message.toCharArray()) {
            if (c != ' ') builder.append(keyStore[c - 'a']);
            else builder.append(c);
        }
        return builder.toString();
    }

    private static String decodeMessageOptimal(String key, String message) {
        char[] keyStore = new char[26];
        boolean[] visited = new boolean[26];
        int ptr = 0;
        for (char c : key.toCharArray()) {
            if (c == ' ') continue;
            int idx = c - 'a';
            if (!visited[idx]) {
                visited[idx] = true;
                keyStore[idx] = (char) (ptr++ + 'a');
            }
        }
        return message.chars() //
                .mapToObj(c -> c != ' ' ? String.valueOf(keyStore[c - 'a']) : String.valueOf((char) c)) //
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv"; // this is a secret
        String result = decodeMessage(key, message);
        System.out.println("Key - " + key + ", Message - " + message);
        System.out.println("Result - " + result);
        //
        key = "eljuxhpwnyrdgtqkviszcfmabo";
        message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb"; // the five boxing wizards jump quickly
        result = decodeMessage(key, message);
        System.out.println("Key - " + key + ", Message - " + message);
        System.out.println("Result - " + result);
    }

}
