package code.ctci.threads;

public class LockAndSemaphore extends Thread {

    private String method;
    private FooReentrantLock fooReentrantLock;
    private FooSemaphore fooSemaphore;

    public LockAndSemaphore(FooReentrantLock foo, FooSemaphore fooSemaphore, String method) {
        this.fooReentrantLock = foo;
        this.fooSemaphore = fooSemaphore;
        this.method = method;
    }

    @Override
    public void run() {
        if (method == "first") {
            // fooReentrantLock.first();
            fooSemaphore.first();
        } else if (method == "second") {
            // fooReentrantLock.second();
            fooSemaphore.second();
        } else if (method == "third") {
            // fooReentrantLock.third();
            fooSemaphore.third();
        }
    }

    public static void main(String[] args) {
        FooReentrantLock foo = new FooReentrantLock();
        FooSemaphore fooSemaphoreObj = new FooSemaphore();
        //
        LockAndSemaphore thread1 = new LockAndSemaphore(foo, fooSemaphoreObj,"first");
        LockAndSemaphore thread2 = new LockAndSemaphore(foo, fooSemaphoreObj, "second");
        LockAndSemaphore thread3 = new LockAndSemaphore(foo, fooSemaphoreObj, "third");
        //
        thread3.start();
        thread2.start();
        thread1.start();
    }

}


