package code.java.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {

    private static boolean isValidSudokuEasy(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int num = board[r][c];
                if (num != '.') {
                    if (!seen.add(num + " in row " + r) || !seen.add(num + " in column " + c)
                            || !seen.add(num + " in block " + (r / 3) + "-" + (c / 3))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != '.' && !isValid(board, row, col, board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(char[][] board, int row, int col, int num) {
        // Check the row, from col 0 to 8
        for (int i = 0; i < board.length; i++) {
            if (i != col && board[row][i] == num) {
                return false;
            }
        }
        // Check the column, from row 0 to row 8
        for (int i = 0; i < board[0].length; i++) {
            if (i != row && board[i][col] == num) {
                return false;
            }
        }
        //
        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;
        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if (row != r && col != c && board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayBoard(char[][] board) {
        int vertical = 1;
        for (char[] row : board) {
            int space = 1;
            for (char ch : row) {
                System.out.print(ch + " ");
                if (space % 3 == 0) System.out.print(" ");
                space++;
            }
            if (vertical % 3 == 0 && vertical < 9) System.out.println();
            vertical++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        boolean isValid = isValidSudokuEasy(board);
        System.out.println("IsValid - " + isValid);
        System.out.println();
        //
        board = new char[][] {
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        isValid = isValidSudoku(board);
        System.out.println("IsValid - " + isValid);
        System.out.println();
    }

}
