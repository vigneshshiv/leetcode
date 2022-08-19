package code.java.strings;

public class Palindrome {

    static int a = Character.getNumericValue('a');
    static int z = Character.getNumericValue('z');
    static int A = Character.getNumericValue('A');
    static int Z = Character.getNumericValue('Z');
    static int space = Character.getNumericValue(' ');

    static {
        String asciiLog = String.format("a - %d, z - %d, A - %d, Z - %d, space - %d", a, z, A, Z, space);
        System.out.println(asciiLog);
    }

    static int getCharNumber(Character c) {
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        } else if (A <= val && val <= Z) {
            return val - A;
        }
        return -1;
    }

    /**
     * This code runs in O(N) time, where N is length of the string
     */
    static void isPermutationOfPalindrome(String str) {
        int countOdd = 0;
        int[] table = new int[z - a + 1];
        for (char c : str.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        System.out.println(str + " is a permutation of palindrome - " + (countOdd <= 1));
    }

    public static void main(String[] args) {
        isPermutationOfPalindrome("madam");
        isPermutationOfPalindrome("Tact Coa");
        isPermutationOfPalindrome("All is well");
        isPermutationOfPalindrome("tactcoapapa");
    }

}
