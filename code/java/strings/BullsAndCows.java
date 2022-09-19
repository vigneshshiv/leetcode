package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/bulls-and-cows/
 */
public class BullsAndCows {

    private static String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] nums = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls += 1;
            } else {
                if (nums[secret.charAt(i) - '0']++ < 0) cows += 1;
                if (nums[guess.charAt(i) - '0']-- > 0) cows += 1;
            }
        }
        return String.format("%dA%dB", bulls, cows);
    }

    public static void main(String[] args) {
        BiConsumer<String[], String> logger = (input, result) -> {
            System.out.println("Secret - " + input[0] + ", Guess - " + input[1] + ", Result - " + result);
        };
        //
        String secret = "1807", guess = "7810";
        String result = getHint(secret, guess);
        logger.accept(new String[]{secret, guess}, result);
        //
        secret = "1123"; guess = "0111";
        result = getHint(secret, guess);
        logger.accept(new String[]{secret, guess}, result);
    }

}
