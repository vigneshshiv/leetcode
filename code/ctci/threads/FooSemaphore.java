package code.ctci.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class FooSemaphore {

    private final int pauseTime = 1000;
    private Semaphore sem1, sem2;

    public FooSemaphore() {
        try {
            sem1 = new Semaphore(1);
            sem2 = new Semaphore(2);
            //
            sem1.acquire();
            sem2.acquire();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void first() {
        try {
            System.out.println("Started executing 1");
            Thread.sleep(pauseTime);
            System.out.println("Finished executing 1");
            sem1.release();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void second() {
        try {
            sem1.acquire();
            sem1.release();
            System.out.println("Started executing 2");
            Thread.sleep(pauseTime);
            System.out.println("Finished executing 2");
            sem2.release();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void third() {
        try {
            sem2.acquire();
            sem2.release();
            System.out.println("Started executing 3");
            Thread.sleep(pauseTime);
            System.out.println("Finished executing 3");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
