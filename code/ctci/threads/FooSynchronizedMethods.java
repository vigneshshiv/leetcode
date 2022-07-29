package code.ctci.threads;

public class FooSynchronizedMethods {

    private String name;

    public FooSynchronizedMethods(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void pause() {
        try {
            Thread.sleep(3000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void methodA(String threadName) {
        System.out.println("Thread - " + threadName + " starting: " + name + ".methodA()");
        pause();
        System.out.println("Thread - " + threadName + " ending: " + name + ".methodA()");
    }

    public void methodB(String threadName) {
        System.out.println("Thread - " + threadName + " starting: " + name + ".methodB()");
        pause();
        System.out.println("Thread - " + threadName + " ending: " + name + ".methodB()");
    }

}
