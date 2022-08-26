package code.java.threads;

import java.util.function.IntConsumer;

/**
 * https://leetcode.com/problems/fizz-buzz-multithreaded/
 */
public class FizzBuzzAdvanced extends Thread {

    private int n;
    private static int current = 1;
    private static Object lock = new Object();

    public FizzBuzzAdvanced (int n) {
        this.n = n;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (current > n) return;
                try {
                    if (current % 15 == 0) {
                        fizzbuzz(() -> { System.out.println("fizzbuzz"); });
                    } else if (current % 3 == 0) {
                        fizz(() -> { System.out.println("fizz"); });
                    } else if (current % 5 == 0) {
                        buzz(() -> { System.out.println("buzz"); });
                    } else {
                        number((num) -> System.out.println(num));
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                current++;
            }
        }
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        printFizz.run();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        printBuzz.run();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        printFizzBuzz.run();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(current);
    }

    public static void main(String[] args) {
        int n = 15;
        Thread[] threads = new Thread[]{
                new FizzBuzzAdvanced(n),
                new FizzBuzzAdvanced(n),
                new FizzBuzzAdvanced(n),
                new FizzBuzzAdvanced(n)
        };
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
