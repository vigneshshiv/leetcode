package code.ctci.operators;

import java.util.function.Consumer;

public class DoubleToBinaryString {

    private static void convertToBinary(double num) {
        Consumer<String> logger = msg -> System.out.println(msg);
        if (num >= 1 || num <= 0) {
            logger.accept("Invalid Number - ERROR");
            return;
        }
        StringBuilder binary = new StringBuilder(".");
        while (num > 0) {
            // Setting a limit on length: 32 characters
            if (binary.length() > 32) {
                logger.accept("Character Limit exceeded");
                return;
            }
            double d = num * 2;
            if (d >= 1) {
                binary.append(1);
                num = d - 1;
            } else {
                binary.append(0);
                num = d;
            }
        }
        logger.accept("Binary String - " + binary.toString());
    }

    private static void convertToBinaryOptimal(double num) {
        Consumer<String> logger = msg -> System.out.println(msg);
        if (num >= 1 || num <= 0) {
            logger.accept("Invalid Number - ERROR");
            return;
        }
        double frac = 0.5;
        StringBuilder binary = new StringBuilder(".");
        while (num > 0) {
            // Setting a limit on length: 32 characters
            if (binary.length() > 32) {
                logger.accept("Character Limit exceeded");
                return;
            }
            if (num >= frac) {
                binary.append(1);
                num -= frac;
            } else {
                binary.append(0);
            }
            frac /= 2;
        }
        logger.accept("Binary String - " + binary.toString());
    }

    public static void main(String[] args) {
        convertToBinary(.125);
        for (int i = 0; i < 1000; i++) {
            double d = i / 1000.0;
            convertToBinary(d);
            convertToBinaryOptimal(d);
        }
    }

}
