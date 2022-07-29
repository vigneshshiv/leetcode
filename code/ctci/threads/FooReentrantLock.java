package code.ctci.threads;

import java.util.concurrent.locks.ReentrantLock;

public class FooReentrantLock {

    private final int pauseTime = 1000;
    private ReentrantLock lock1, lock2;

    public FooReentrantLock() {
        try {
            lock1 = new ReentrantLock();
            lock2 = new ReentrantLock();
            //
            lock1.lock();
            lock2.lock();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void first() {
        try {
            System.out.println("Started executing 1");
            Thread.sleep(pauseTime);
            System.out.println("Finished executing 1");
            lock1.unlock();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void second() {
        try {
            lock1.lock();
            lock1.unlock();
            System.out.println("Started executing 2");
            Thread.sleep(pauseTime);
            System.out.println("Finished executing 2");
            lock2.unlock();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void third() {
        try {
            lock2.lock();
            lock2.unlock();
            System.out.println("Started executing 3");
            Thread.sleep(pauseTime);
            System.out.println("Finished executing 3");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
