import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CyclicBarrier_ {
    public static void main(String[] args){

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long mills = (long) (Math.random() * 5000 + 1000);
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "date is being prepared");

                    try {
                        Thread.sleep(mills);
                        System.out.println(name + "date is ready");

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(name + ": continue work");

                }
            }).start();
        }

    }

}
