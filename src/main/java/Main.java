import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        CountDownLatch counter = new CountDownLatch(3);

        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i<1_000_000;i++){
                    if (i % 2 == 0){
                        sum += i;
                    }
                }
                System.out.println(sum);
                counter.countDown();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i<1_000_000;i++){
                    if (i % 7 == 0){
                        sum += i;
                    }
                }
                System.out.println(sum);
                counter.countDown();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> array = new ArrayList<>(1000);
                for (int i = 0; i < 1000; i++) {
                    array.add((int)(Math.random()*1000));
                }
                int c = 0;
                for (Integer x : array){
                    if (x % 2 == 0){
                        c++;
                    }
                }
                System.out.println(c);
                counter.countDown();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            counter.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long after = System.currentTimeMillis();
        long result = after-before;
        System.out.println(result);
    }
}
