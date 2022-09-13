package code.java.graphs;

import code.java.utils.MethodsUtility;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {

    private static final int NO_DIRS = 4;
    private static int[] DIRS = {0, 1, 0, -1, 0};

    private static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;
        Set<String> visited = new HashSet<>();
        fill(image, sr, sc, image[sr][sc], color, visited);
        return image;
    }

    private static void fill(int[][] image, int sr, int sc, int color, int newColor, Set<String> visited) {
        boolean rowInbounds = (0 <= sr && sr < image.length);
        boolean colInbounds = (0 <= sc && sc < image[0].length);
        // Check row and column bounds
        if (!rowInbounds || !colInbounds) return;
        // If color is not same as existing then skip
        if (image[sr][sc] != color) return;
        // Change the color
        image[sr][sc] = newColor;
        String key = sr + "#" + sc;
        visited.add(key);
        for (int idx = 0; idx < NO_DIRS; idx++) {
            key = sr + DIRS[idx] + "#" + sc + DIRS[idx + 1];
            if (!visited.contains(key)) {
                fill(image, sr + DIRS[idx], sc + DIRS[idx + 1], color, newColor, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        };
        int sr = 1, sc = 1, color = 2;
        MethodsUtility.printArray(image, image.length, image[0].length);
        floodFill(image, sr, sc, color);
        MethodsUtility.printArray(image, image.length, image[0].length);
        //
        image = new int[][] {
                {0, 0, 0}, {0, 0, 0}
        };
        sr = 0; sc = 0; color = 0;
        MethodsUtility.printArray(image, image.length, image[0].length);
        floodFill(image, sr, sc, color);
        MethodsUtility.printArray(image, image.length, image[0].length);
    }

}
