import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger value = new AtomicInteger();

    public void inc() {
        value.getAndIncrement();
    }

    public void dic() {
        value.getAndDecrement();
    }

    public int getValue1() {
        return value.intValue();
    }
}
