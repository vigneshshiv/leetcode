package code.ctci.threads;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FizzBuzzThreadOptimal extends Thread {

    private static Object lock = new Object();
    private static int current = 1;
    private int max;
    private Predicate<Integer> validate;
    private Function<Integer, String> printer;

    public FizzBuzzThreadOptimal(int n, Predicate<Integer> validator, Function<Integer, String> logger) {
        this.max = n;
        this.validate = validator;
        this.printer = logger;
    }

    @Override
    public void run() {
        Consumer<String> logger = System.out::println;
        while (true) {
            synchronized (lock) {
                if (current > max) return;
                if (validate.test(current)) {
                    logger.accept(printer.apply(current));
                    current++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        Thread[] threads = new Thread[] {
                new FizzBuzzThreadOptimal(n, i -> i % 3 == 0 && i % 5 == 0, i -> "FizzBuzz"),
                new FizzBuzzThreadOptimal(n, i -> i % 3 == 0 && i % 5 != 0, i -> "Fizz"),
                new FizzBuzzThreadOptimal(n, i -> i % 3 != 0 && i % 5 == 0, i -> "Buzz"),
                new FizzBuzzThreadOptimal(n, i -> i % 3 != 0 && i % 5 != 0, i -> String.valueOf(i))
        };
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
