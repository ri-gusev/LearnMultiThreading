public class Counter {

    private volatile int value = 0;

    public synchronized void inc() {
        value++;
    }

    public synchronized void dic() {
        value--;
    }

    public int getValue1() {
        return value;
    }
}
