import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {

        List<Integer> array = new CopyOnWriteArrayList<>();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Object obj = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100);
                        array.add(i);
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100);
                        array.add(i);
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(array.size());

        for (int x : array){
            System.out.println(x);
        }

    }
}
