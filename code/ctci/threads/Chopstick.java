package code.ctci.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private Lock lock;
    private int number;

    public Chopstick(int n) {
        this.lock = new ReentrantLock();
        this.number = n;
    }

    public void pickUp() {
        this.lock.lock();
    }

    public void putDown() {
        this.lock.unlock();
    }

    public int getNumber() {
        return this.number;
    }

}
