package code.ctci.strings;

import java.util.Objects;

public class UniqueCharacter {

    /**
     * Time complexity - O(n)
     * Space complexity - O(1)
     */
    static boolean isUniqueChars(String str) {
        if (Objects.isNull(str) || str.isEmpty() || str.length() > 128) {
            return false;
        }
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(1)
     */
    static boolean isUniqueCharsBitVector(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            System.out.println("CharAt(i) - " + str.charAt(i) + ", CharAt(i)-'a' - " + val);
            System.out.println("1 << val - " + (1 << val) + ", checker & (1 << val) - " + (checker & (1 << val)));
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
            System.out.println("Checker - " + checker);
        }
        return true;
    }

    static void printBitManipulations() {
        System.out.println("1 << 0 - " + (1 << 0) + ", 1 >> 0 - " + (1 >> 0));
        System.out.println("1 << 2 - " + (1 << 2) + ", 1 >> 2 - " + (1 >> 2));
        System.out.println("1 & 0 - " + (1 & 0) + ", 1 | 0 - " + (1 | 0));
        System.out.println("2 & 3 - " + (2 & 3) + ", 3 & 2 - " + (3 & 2));
        System.out.println("3 & 1 - " + (3 & 1) + ", 1 & 3 - " + (1 & 3));
        System.out.println("3 | 1 - " + (3 | 1) + ", 1 | 3 - " + (1 | 3));
    }

    public static void main(String[] args) {
        System.out.println("[strings] Is Characters unique - " + isUniqueChars("string"));
        System.out.println("[strings] Is Characters unique - " + isUniqueCharsBitVector("abca"));
        printBitManipulations();
    }

}
