import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.*;

public class RaceTask {
    public static void main(String[] args){

        CountDownLatch countDownLatch1 = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(3);
        CountDownLatch countDownLatch2 = new CountDownLatch(10);

        // Thread is a car
        for (int i = 0; i < 10; i++) {
            final int numberOfCar = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long before = System.currentTimeMillis();

                    // Preparing

                    long millsPrepared = (long) (Math.random() * 5000 + 1000);
                    System.out.println(numberOfCar + " started preparing");
                    try {
                        Thread.sleep(millsPrepared);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    countDownLatch1.countDown();
                    System.out.println(numberOfCar + " finished preparing");
                    try {
                        countDownLatch1.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // FirstRoad

                    System.out.println(numberOfCar + " start roadFirst");
                    long millsForRoadFirst = (long) (Math.random() * 5000 + 1000);
                    try {
                        Thread.sleep(millsForRoadFirst);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(numberOfCar + " finish roadFirst");

                    //Tunnel

                    long millsForTunnel = (long) (Math.random() * 5000 + 1000);
                    try {
                        semaphore.acquire();
                        System.out.println(numberOfCar + " start tunnel");
                        Thread.sleep(millsForTunnel);
                        System.out.println(numberOfCar + " finish tunnel");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }

                    // Second Road

                    long millsForSecondRoad = (long) (Math.random() * 5000 + 1000);
                    System.out.println(numberOfCar + " start roadSecond");
                    try {
                        Thread.sleep(millsForSecondRoad);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(numberOfCar + " finish roadSecond");
                    long after = System.currentTimeMillis();
                    long timeOfCar = after - before;

                    countDownLatch2.countDown();
                    try {
                        countDownLatch2.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }).start();
        }

    }
}
