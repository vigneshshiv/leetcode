package code.ctci.numbers;

public class PrimeNumber {

    /**
     * This code runs in O(sqrt(n)) time
     */
    static boolean isPrime(int n) {
        for (int x = 2; x <= Math.sqrt(n); x++) {
            if (n % x == 0) {
                System.out.println("Prime (N) - " + n + ", divided by - " + x);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("(N) - 33 is a Prime Number? -- " + isPrime(33));
    }

}
