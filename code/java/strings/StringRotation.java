package code.java.strings;

public class StringRotation {

    /**
     * Time complexity: O(N) time, where substring is runs in O(A+B) time
     * Space complexity: O(1) space
     */
    static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        // Check that s1 and s2 are equal length and not empty
        if (len == s2.length() && len > 0) {
            // Concatenate s1 and s2 within new buffer
            String s1s1 = s1 + s1;
            return s1s1.contains(s2);
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println("Is " + s2 + " is substring of " + s1 + " - " + isRotation(s1, s2));
    }

}
