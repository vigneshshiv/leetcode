package code.java.numbers;

public class PrimeNumber {

    /**
     * This code runs in O(sqrt(n)) time
     */
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        int sqrtNumber = (int) Math.sqrt(n);
        for (int x = 2; x <= sqrtNumber; x++) {
            if (n % x == 0) {
                // System.out.println("Prime (N) - " + n + ", divided by - " + x);
                return false;
            }
        }
        return true;
    }

    private static void primeNumbers(int n) {
        for (int x = 1; x <= n; x++) {
            if (isPrime(x)) System.out.println(x + " is a prime number.");
        }
    }

    public static void main(String[] args) {
        System.out.println("(N) - 33 is a Prime Number? -- " + isPrime(33));
        primeNumbers(100);
    }

}
