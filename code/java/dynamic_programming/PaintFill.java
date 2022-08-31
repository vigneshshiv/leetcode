package code.java.dynamic_programming;

import java.util.Arrays;
import java.util.Objects;

public class PaintFill {

    public enum Color {
        BLACK, WHITE, RED, YELLOW, GREEN
    }

    private static String getColor(Color color) {
        return switch (color) {
            case BLACK -> "B";
            case WHITE -> "W";
            case RED -> "R";
            case YELLOW -> "Y";
            case GREEN -> "G";
            default -> "X";
        };
    }

    private static void printScreen(Color[][] screen) {
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen[0].length; c++) {
                System.out.print(getColor(screen[r][c]));
            }
            System.out.println();
        }
    }

    private static void paintFill(Color[][] screen, int r, int c, Color new_color) {
        if (Objects.equals(screen[r][c], new_color)) {
            return;
        }
        paintFill(screen, r, c, screen[r][c], new_color);
    }

    private static void paintFill(Color[][] screen, int r, int c, Color old_color, Color new_color) {
        // Base case
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
            return;
        }
        if (Objects.equals(screen[r][c], old_color)) {
            screen[r][c] = new_color;
            // Move up
            paintFill(screen, r - 1, c, old_color, new_color);
            // Down
            paintFill(screen, r + 1, c, old_color, new_color);
            // Left
            paintFill(screen, r, c - 1, old_color, new_color);
            // Right
            paintFill(screen, r, c + 1, old_color, new_color);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        Color[][] screen = new Color[n][n];
        Arrays.setAll(screen, r -> {
            Arrays.setAll(screen[r], c -> Color.BLACK);
            return screen[r];
        });
        int[][] green_colors = {
                {0, 3}, {1, 1}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {3, 2}
        };
        // Change specific index to green
        for (int[] index : green_colors) {
            int r = index[0], c = index[1];
            screen[r][c] = Color.GREEN;
        }
        printScreen(screen);
        paintFill(screen, 2, 2, Color.WHITE);
        System.out.println();
        printScreen(screen);
    }

}
