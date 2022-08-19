package code.java.operators;

import java.util.ArrayList;
import java.util.List;

public class FlipBits {

    /**
     * Time complexity: O(b), where b is the length of the sequence
     * Space complexity: O(b) space
     */
    private static int longestSequence(int n) {
        if (n == -1) return Integer.BYTES * 8;
        List<Integer> sequences = getAlternatingSequences(n);
        return findLongestSequence(sequences);
    }

    private static List<Integer> getAlternatingSequences(int n) {
        List<Integer> sequences = new ArrayList<>();
        int searchingFor = 0;
        int counter = 0;
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((n & 1) != searchingFor) {
                sequences.add(counter);
                searchingFor = n & 1; // Flip 1 to 0 or 0 to 1
                counter = 0;
            }
            counter++;
            n >>>= 1;
        }
        sequences.add(counter);
        return sequences;
    }

    private static int findLongestSequence(List<Integer> sequences) {
        int maxSeq = 1;
        for (int i = 0; i < sequences.size(); i += 2) {
            int zeroSeq = sequences.get(i);
            int oneSeqRight = i - 1 >= 0 ? sequences.get(i - 1) : 0;
            int oneSeqLeft = i + 1 < sequences.size() ? sequences.get(i + 1) : 0;
            int thisSeq = 0;
            if (zeroSeq == 1) {
                thisSeq = oneSeqLeft + 1 + oneSeqRight;
            } else if (zeroSeq > 1) { // Add a zero to either side
                thisSeq = 1 + Math.max(oneSeqRight, oneSeqLeft);
            } else if (zeroSeq == 0) { // No zero, but take either side
                thisSeq = Math.max(oneSeqRight, oneSeqLeft);
            }
            maxSeq = Math.max(thisSeq, maxSeq);
        }
        return maxSeq;
    }

    /**
     * Time complexity: O(b), where b is the length of the sequence
     * Space complexity: O(1) space
     */
    private static int longestSequenceOptimal(int n) {
        // If all 1s, this is already the longest sequence
        if (~n == 0) return Integer.BYTES * 8;
        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1;
        while (n != 0) {
            if ((n & 1) == 1) { // Current bit is 1
                currentLength++;
            } else if ((n & 1) == 0) { // Current bit is 0
                // Update to 0 (if next bit is 0) or current length (if next bit is 1)
                previousLength = (n & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            n >>>= 1;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int sequence = longestSequence(19);
        System.out.println("19 longest sequence - " + sequence);
        sequence = longestSequenceOptimal(19);
        System.out.println("19 longest sequence optimal - " + sequence);
        sequence = longestSequenceOptimal(1775);
        System.out.println("1775 longest sequence optimal - " + sequence);

    }

}
