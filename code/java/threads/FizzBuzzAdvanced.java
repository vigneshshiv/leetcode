package code.java.threads;

import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * https://leetcode.com/problems/fizz-buzz-multithreaded/
 */
public class FizzBuzzAdvanced {

    private int n;
    private final AtomicInteger counter;

    public FizzBuzzAdvanced (int n) {
        this.n = n;
        counter = new AtomicInteger(1);
    }

    private void updateToNext(int count) {
        counter.compareAndSet(count, count + 1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 3 == 0 && count % 5 != 0) {
                printFizz.run();
                updateToNext(count);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 5 == 0 && count % 3 != 0) {
                printBuzz.run();
                updateToNext(count);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 3 == 0 && count % 5 == 0) {
                printFizzBuzz.run();
                updateToNext(count);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 3 != 0 && count % 5 != 0) {
                printNumber.accept(count);
                updateToNext(count);
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        Thread[] threads = new Thread[] {
                new Thread(() -> new FizzBuzzAdvanced(n)),
                new Thread(() -> new FizzBuzzAdvanced(n)),
                new Thread(() -> new FizzBuzzAdvanced(n)),
                new Thread(() -> new FizzBuzzAdvanced(n))
        };
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
