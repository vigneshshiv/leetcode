package code.java.patterns;

public class PyramidPattern {

    /**
     * Pattern format
     * *
     * **
     * ***
     * ****
     */
    private static void pattern1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *     #
     *    ##
     *   ###
     *  ####
     * #####
     */
    private static void pattern2(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("  ");
            }
            for (int k = 0; k < i + 1; k++) {
                System.out.print("# ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     * ****
     * ***
     * **
     * *
     */
    private static void pattern3(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     * 1
     * 1 2 
     * 1 2 3
     * 1 2 3 4
     * 1 2 3 4 5
     */
    private static void pattern4(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print((j + 1) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * *
     * **
     * ***
     * **
     * *
     */
    private static void pattern5(int n) {
        int rows = 2 * n;
        for (int i = 0; i < rows; i++) {
            int cols = i > n ? (rows - i) : i;
            for (int j = 0; j < cols; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     */
    private static void pattern6(int n) {
        for (int i = 0; i < n; i++) {
            int noOfSpaces = n - i - 1;
            for (int j = 0; j < noOfSpaces; j++) {
                System.out.print("  ");
            }
            for (int k = 0; k < 2 * i + 1; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     */
    private static void pattern7(int n) {
        for (int i = 0; i < n; i++) {
            int noOfSpaces = i;
            for (int j = 0; j < noOfSpaces; j++) {
                System.out.print("  ");
            }
            for (int k = 0; k < 2 * (n - i) - 1; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     */
    private static void pattern8(int n) {
        int rows = 2 * n - 1;
        for (int i = 0; i < rows; i++) {
            int noOfSpaces = i >= n ? (i - n + 1) : (n - i - 1);
            for (int j = 0; j < noOfSpaces; j++) {
                System.out.print("  ");
            }
            int cols = i >= n ? 2 * (rows - i - 1) + 1 : (2 * i) + 1;
            for (int k = 0; k < cols; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *      *
     *     * *
     *    * * *
     *   * * * *
     *  * * * * *
     *   * * * *
     *    * * *
     *     * *
     *      *
     */
    private static void pattern9(int n) {
        int rows = 2 * n - 1;
        for (int i = 0; i < rows; i++) {
            int noOfSpaces = i >= n ? (i - n) + 1: (n - i) - 1;
            for (int j = 0; j < noOfSpaces; j++) {
                System.out.print(" ");
            }
            int cols = i >= n ? rows - i : i + 1;
            for (int k = 0; k < cols; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *     1
     *    212
     *   32123
     *  4321234
     * 543212345
     */
    private static void pattern10(int n) {
        for (int i = 1; i <= n; i++) {
            int noOfSpaces = n - i;
            for (int j = 0; j < noOfSpaces; j++) {
                System.out.print("  ");
            }
            for (int k = i; k >= 1; k--) {
                System.out.print(k + " ");
            }
            for (int k = 2; k <= i; k++) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *     1
     *    212
     *   32123
     *  4321234
     * 543212345
     *  4321234
     *   32123
     *    212
     *     1
     */
    private static void pattern11(int n) {
        int rows = 2 * n;
        for (int i = 1; i < rows; i++) {
            int noOfSpaces = i > n ? (i - n) : (n - i);
            for (int j = 0; j < noOfSpaces; j++) {
                System.out.print("  ");
            }
            int cols = i > n ? rows - i : i;
            for (int k = cols; k >= 1; k--) {
                System.out.print(k + " ");
            }
            for (int k = 2; k <= cols; k++) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Pattern format
     *
     *  4 4 4 4 4 4 4
     *  4 3 3 3 3 3 4
     *  4 3 2 2 2 3 4
     *  4 3 2 1 2 3 4
     *  4 3 2 2 2 3 4
     *  4 3 3 3 3 3 4
     *  4 4 4 4 4 4 4
     */
    private static void pattern12(int n) {
        int rows = 2 * n;
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= rows; j++) {
                int value = n - (Math.min(Math.min(i, j), Math.min(rows - i, rows - j)));
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Pattern 1
        pattern1(5);
        // Pattern 2
        pattern2(5);
        //
        pattern3(5);
        //
        pattern4(5);
        //
        pattern5(5);
        //
        pattern6(5);
        //
        pattern7(5);
        //
        pattern8(5);
        //
        pattern9(5);
        //
        pattern10(5);
        //
        pattern11(5);
        //
        pattern12(4);
    }

}
