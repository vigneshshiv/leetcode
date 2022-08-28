package code.java.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/n-queens/
 */
public class NQueens {

    private static final int GRID_SIZE = 4;

    private static Supplier<List<String>> emptyList = () -> new ArrayList<>();

    private static List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        Arrays.setAll(board, r -> {
            Arrays.fill(board[r], false);
            return board[r];
        });
        return placeNQueens(board, 0);
    }

    private static List<List<String>> placeNQueens(boolean[][] board, int row) {
        if (row == board.length) {
            return boardPlacement(board);
        }
        List<List<String>> result = new ArrayList<>();
        // Placing the queen and checking for every row and col
        for (int col = 0; col < board.length; col++) {
            if (isSafeToPlace(board, row, col)) {
                board[row][col] = true;
                result.addAll(placeNQueens(board, row + 1));
                board[row][col] = false;
            }
        }
        return result;
    }

    private static List<List<String>> boardPlacement(boolean[][] board) {
        List<List<String>> result = new ArrayList<>();
        List<String> placement = emptyList.get();
        for (boolean[] row : board) {
            StringBuilder builder = new StringBuilder();
            for (boolean element : row) {
                if (element) {
                    builder.append("Q");
                } else {
                    builder.append(".");
                }
            }
            placement.add(builder.toString());
        }
        result.add(placement);
        return result;
    }

    private static int placeQueens(boolean[][] board, int row) {
        if (row == board.length) {
            printBoard(board);
            return 1;
        }
        int count = 0;
        // Placing the queen and checking for every row and col
        for (int col = 0; col < board.length; col++) {
            if (isSafeToPlace(board, row, col)) {
                board[row][col] = true;
                count += placeQueens(board, row + 1);
                board[row][col] = false;
            }
        }
        return count;
    }

    private static boolean isSafeToPlace(boolean[][] board, int row, int col) {
        // Vertical
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }
        // Left Diagonal
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[row - i][col - i]) {
                return false;
            }
        }
        // Right Diagonal
        int maxRight = Math.min(row, board.length - col - 1);
        for (int i = 1; i <= maxRight; i++) {
            if (board[row - i][col + i]) {
                return false;
            }
        }
        return true;
    }

    private static void printBoard(boolean[][] board) {
        drawLine();
        for (boolean[] row : board) {
            System.out.print("|");
            for (boolean element : row) {
                if (element) {
                    System.out.print("Q|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.print("\n");
            drawLine();
        }
        System.out.println();
    }

    private static void drawLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < GRID_SIZE * 2 + 1; i++) {
            line.append('-');
        }
        System.out.println(line.toString());
    }

    private static void printBoard(Integer[] columns) {
        drawLine();
        for (int r = 0; r < GRID_SIZE; r++) {
            System.out.print("|");
            for (int c = 0; c < GRID_SIZE; c++) {
                if (columns[r] == c) {
                    System.out.print("Q|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.print("\n");
            drawLine();
        }
        System.out.println("");
    }

    private static void printBoards(List<Integer[]> boards) {
        IntStream.range(0, boards.size()).forEach(idx -> printBoard(boards.get(idx)));
    }

    /**
     * Check if (row1, column1) is a valid spot for a queen by checking if there is queen in the same column or diagonal.
     * We don't need to check it for queens in the same row,
     * because the calling placeQueen only attempts to place one queen at a time. We know this row is empty.
     */
    private static boolean checkValid(Integer[] columns, int row1, int column1) {
        for (int row2 = 0; row2 < row1; row2++) {
            // Check if (row2, column2) invalidates (row1, column1) as a queen spot
            int column2 = columns[row2];
            // Check if row have a queen in the same column
            if (column1 == column2) {
                return false;
            }
            // Check diagonals.
            // If the distance between the columns equals the distance between the rows, then they are in the same diagonal.
            int rowDistance = row1 - row2;
            int columnDistance = Math.abs(column2 - column1);
            if (rowDistance == columnDistance) {
                return false;
            }
        }
        return true;
    }

    private static void placeQueens(int row, Integer[] columns, List<Integer[]> results) {
        if (row == GRID_SIZE) {
            results.add(columns.clone());
        } else {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (checkValid(columns, row, col)) {
                    columns[row] = col;
                    placeQueens(row + 1, columns, results);
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
        List<Integer[]> results = new ArrayList<>();
        Integer[] columns = new Integer[GRID_SIZE];
        Arrays.fill(columns, -1);
        placeQueens(0, columns, results);
        printBoards(results);
        System.out.println("Results Size - " + results.size());
        */
        //
        /*
        boolean[][] board = new boolean[GRID_SIZE][GRID_SIZE];
        Arrays.setAll(board, r -> {
            Arrays.fill(board[r], false);
            return board[r];
        });
        int count = placeQueens(board, 0);
        System.out.println("No of ways to place in " + (GRID_SIZE + "x" + GRID_SIZE) + " board - " + count);
        */
        //
        List<List<String>> result = solveNQueens(GRID_SIZE);
        System.out.println(result);
    }

}
