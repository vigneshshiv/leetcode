package code.java.arrays;

import java.util.Arrays;

public class FlipImage {

    /**
     * https://leetcode.com/problems/flipping-an-image/
     */
    private static void flipAndInvertImage(int[][] image) {
        for (int[] pixels : image) {
            for (int i = 0; i < (pixels.length + 1) / 2; i++) {
                int other = pixels[pixels.length - i - 1] ^ 1;
                pixels[pixels.length - i - 1] = pixels[i] ^ 1;
                pixels[i] = other;
            }
        }
        System.out.println("Converted Image - " + Arrays.deepToString(image));
    }

    /**
     * Converted Image with Two pointers
     */
    private static void flipAndInvertImageWithTwoPointers(int[][] image) {
        for (int[] pixels : image) {
            int low = 0, high = pixels.length - 1;
            while (low <= high) {
                if (pixels[low] == pixels[high]) {
                    int other = pixels[low] ^ 1;
                    pixels[low] = pixels[high] ^ 1;
                    pixels[high] = other;
                }
                low++;
                high--;
            }
        }
        System.out.println("Converted Image With Two Pointers - " + Arrays.deepToString(image));
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 0}, {1, 0, 1}, {0, 0, 0}
        };
        // flipAndInvertImage(image);
        flipAndInvertImageWithTwoPointers(image);
        int[][] photo = {
                {1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}
        };
        // flipAndInvertImage(photo);
        flipAndInvertImageWithTwoPointers(photo);
    }

}
