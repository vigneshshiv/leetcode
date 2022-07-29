package code.ctci.threads;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FizzBuzzThread extends Thread {

    private static Object lock = new Object();
    private static int current = 1;
    private int max;
    private boolean div3, div5;
    private String toPrint;

    public FizzBuzzThread(int n, boolean div3, boolean div5, String message) {
        this.max = n;
        this.div3 = div3;
        this.div5 = div5;
        this.toPrint = message;
    }

    @Override
    public void run() {
        Consumer<Supplier<String>> printer = str -> System.out.println(str.get());
        while (true) {
            synchronized (lock) {
                if (current > max) return;
                if ((current % 3 == 0) == div3 && (current % 5 == 0) == div5) {
                    printer.accept(() -> Objects.nonNull(toPrint) ? toPrint : String.valueOf(current));
                    current++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        Thread[] threads = new Thread[] {
                new FizzBuzzThread(n, true, true, "FizzBuzz"),
                new FizzBuzzThread(n, true, false, "Fizz"),
                new FizzBuzzThread(n, false, true, "Buzz"),
                new FizzBuzzNumberThread(n, false, false)
        };
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
