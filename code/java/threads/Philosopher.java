package code.java.threads;

import code.java.utils.MethodsUtility;

import java.util.function.Function;

public class Philosopher extends Thread {

    private final int maxPause = 100;
    private int bites = 1;
    private Chopstick lower, higher;
    private int index;

    public Philosopher(int i, Chopstick left, Chopstick right) {
        this.index = i;
        if (left.getNumber() < right.getNumber()) {
            this.lower = left;
            this.higher = right;
        } else {
            this.lower = right;
            this.higher = lower;
        }
    }

    public void eat() {
        System.out.println("Philosopher " + index + ": start eating");
        pickUp();
        chew();
        putDown();
        System.out.println("Philosopher " + index + ": done eating");
    }

    public void pickUp() {
        this.pause();
        this.lower.pickUp();
        this.pause();
        this.higher.pickUp();
        this.pause();
    }

    public void chew() {
        System.out.println("Philosopher " + index + ": eating");
        this.pause();
    }

    public void pause() {
        try {
            int index = MethodsUtility.randomIntRange(0, maxPause);
            Thread.sleep(index);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void putDown() {
        this.higher.putDown();
        this.lower.putDown();
    }

    @Override
    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }

    public static void main(String[] args) {
        int size = 3;
        Function<Integer, Integer> leftOf = value -> value;
        Function<Integer, Integer> rightOf = value -> (value + 1) % size;
        //
        Chopstick[] chopsticks = new Chopstick[size + 1];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick(i);
        }
        //
        Philosopher[] philosophers = new Philosopher[size];
        Chopstick left, right;
        for (int i = 0; i < size; i++) {
            left = chopsticks[leftOf.apply(i)];
            right = chopsticks[rightOf.apply(i)];
            philosophers[i] = new Philosopher(i, left, right);
        }
        //
        for (int i = 0; i < size; i++) {
            philosophers[i].start();
        }
    }

}
