package code.ctci.threads;

public class FooSynchronizedThread extends Thread {

    private FooSynchronizedMethods fooSynchronizedMethods;
    private String threadName;
    private String methodName;

    public FooSynchronizedThread(FooSynchronizedMethods fooSynchronizedMethods, String threadName, String methodName) {
        this.fooSynchronizedMethods = fooSynchronizedMethods;
        this.threadName = threadName;
        this.methodName = methodName;
    }

    @Override
    public void run() {
        if (methodName.equals("A")) {
            fooSynchronizedMethods.methodA(threadName);
        } else {
            fooSynchronizedMethods.methodB(threadName);
        }
    }

    public static void main(String[] args) {
        System.out.println("FooSynchronized with Same instance.");
        FooSynchronizedMethods foo1A = new FooSynchronizedMethods("foo1A");
        FooSynchronizedThread thread1a = new FooSynchronizedThread(foo1A, "1A", "A");
        FooSynchronizedThread thread2a = new FooSynchronizedThread(foo1A, "2A", "A");
        thread1a.start();
        thread2a.start();
        while (thread1a.isAlive() || thread2a.isAlive()) { }
        System.out.println();
        //
        System.out.println("FooSynchronized with Different instance.");
        FooSynchronizedMethods fooD1A = new FooSynchronizedMethods("fooD1A");
        FooSynchronizedMethods fooD2A = new FooSynchronizedMethods("fooD2A");
        FooSynchronizedThread threadD1A = new FooSynchronizedThread(foo1A, "D1A", "A");
        FooSynchronizedThread threadD2A = new FooSynchronizedThread(foo1A, "D2A", "A");
        threadD1A.start();
        threadD2A.start();
        while (threadD1A.isAlive() || threadD2A.isAlive()) { }
        System.out.println();
        //
        System.out.println("FooSynchronized with Same instance but calling different methods.");
        FooSynchronizedMethods foo = new FooSynchronizedMethods("foo");
        FooSynchronizedThread threadFoo1A = new FooSynchronizedThread(foo, "Foo1A", "A");
        FooSynchronizedThread threadFoo1B = new FooSynchronizedThread(foo, "Foo1B", "B");
        threadFoo1A.start();
        threadFoo1B.start();
    }

}
