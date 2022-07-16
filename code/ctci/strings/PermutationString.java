package code.ctci.strings;

public class PermutationString {

    /**
     * This code runs in O(n!) time - in terms of No. of characters
     */
    static void permutation(String str, String prefix) {
        System.out.println("str - " + str + ", prefix - " + prefix);
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                System.out.println("1st - " + str.substring(0, i) + ", 2nd - " + str.substring(i + 1) + ", Rem - " + rem + ", CharAt(i) - " + str.charAt(i));
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        permutation("ABC", "");
    }

}
