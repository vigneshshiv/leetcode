package code.java.backtracking;

/**
 * https://leetcode.com/problems/sudoku-solver/
 */
public class SudokuSolver {

    /**
     * Time complexity: O(9^n^2)
     * Space complexity: O(n)
     */
    private static void solveSudoku(char[][] board) {
        boolean isSolved = solveBoard(board);
        if (isSolved) {
            // Print the board
            displayBoard(board);
        } else {
            System.out.println("Board cannot be solved");
        }
        System.out.println("-------------------");
        System.out.println();
    }

    private static boolean solveBoard(char[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;
        // Empty Item, so fill that row & col
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            // If empty left to be filled found, then break the loop
            if (!emptyLeft) {
                break;
            }
        }
        if (emptyLeft) {
            return true;
        }
        // Backtrack
        for (char number = '1'; number <= '9'; number++) {
            if (isSafeToPlace(board, row, col, number)) {
                board[row][col] = number;
                if (solveBoard(board)) {
                    return true;
                } else {
                    // Backtrack
                    board[row][col] = '.';
                }
            }
        }
        return false; // Cannot be solved
    }

    private static boolean isSafeToPlace(char[][] board, int row, int col, char num) {
        // Check the row, from col 0 to 8
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // Check the column, from row 0 to row 8
        for (char[] nums : board) {
            if (nums[col] == num) {
                return false;
            }
        }
        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;
        //
        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if (board[r][c] == num) {
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
        solveSudoku(board);
        //
        board = new char[][] {
                {'.','.','.','8','2','6','.','.','1'},
                {'.','.','1','.','.','.','.','4','7'},
                {'.','.','5','.','4','.','.','.','.'},
                {'3','.','.','.','.','1','.','.','.'},
                {'.','7','2','.','8','.','3','5','.'},
                {'.','.','.','6','.','.','.','.','4'},
                {'.','.','.','.','6','.','7','.','.'},
                {'8','2','.','.','.','.','6','.','.'},
                {'7','.','.','5','3','2','.','.','.'}
        };
        solveSudoku(board);
    }

}
