package code.ctci.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final int GRID_SIZE = 4;

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
        List<Integer[]> results = new ArrayList<>();
        Integer[] columns = new Integer[GRID_SIZE];
        Arrays.fill(columns, -1);
        placeQueens(0, columns, results);
        printBoards(results);
        System.out.println("Results Size - " + results.size());
    }

}
