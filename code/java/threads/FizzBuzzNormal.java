package code.java.threads;

import java.util.function.Consumer;

public class FizzBuzzNormal {

    public static void fizzbuzz(int n) {
        Consumer<String> printer = System.out::println;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) printer.accept("FizzBuzz");
            else if (i % 3 == 0) printer.accept("Fizz");
            else if (i % 5 == 0) printer.accept("Buzz");
            else printer.accept(String.valueOf(i));
        }
    }

    public static void main(String[] args) {
        fizzbuzz(15);
    }

}
