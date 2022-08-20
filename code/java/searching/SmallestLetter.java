package code.java.searching;

/**
 * 744. Find Smallest Letter Greater Than Target
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 */
public class SmallestLetter {

    private static char findNextGreatestLetter(char[] letters, char target) {
        int low = 0, mid = 0, high = letters.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            // Order of checking is very important
            if (target < letters[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return letters[low % letters.length];
    }

    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        char result = findNextGreatestLetter(letters, target);
        System.out.println("Input - " + String.valueOf(letters) + ", Target - " + target + ", Result - " + result);
        //
        target = 'c';
        result = findNextGreatestLetter(letters, target);
        System.out.println("Input - " + String.valueOf(letters) + ", Target - " + target + ", Result - " + result);
        //
        target = 'd';
        result = findNextGreatestLetter(letters, target);
        System.out.println("Input - " + String.valueOf(letters) + ", Target - " + target + ", Result - " + result);
        //
        letters = new char[] {'a', 'b'};
        target = 'z';
        result = findNextGreatestLetter(letters, target);
        System.out.println("Input - " + String.valueOf(letters) + ", Target - " + target + ", Result - " + result);
    }

}
