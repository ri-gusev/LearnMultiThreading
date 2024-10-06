import java.util.concurrent.*;

public class RaceTask {

    private static final int MAX_CAR_IN_TUNNEL = 3;
    private static final int CARS_COUNT = 10;

    private static final Semaphore tunnelSemaphore = new Semaphore(MAX_CAR_IN_TUNNEL);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final CountDownLatch countDownLatch1 = new CountDownLatch(CARS_COUNT);
    private static final ConcurrentHashMap<Integer, Long> results = new ConcurrentHashMap<>();
    private static final CountDownLatch countDownLatch2 = new CountDownLatch(CARS_COUNT);

    private static int winnerIndex = -1;
    private static final Object monitor = new Object();

    public static void main(String[] args){
        for (int i = 0; i < CARS_COUNT; i++) {
            final int numOfCar = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    prepare(numOfCar);
                    try {
                        countDownLatch1.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    long before = System.currentTimeMillis();
                    roadFirst(numOfCar);
                    tunnel(numOfCar);
                    roadSecond(numOfCar);
                    synchronized (monitor) {
                        if (winnerIndex == -1){
                            winnerIndex = numOfCar;
                        }
                    }
                    long after = System.currentTimeMillis();
                    long result = after - before;
                    results.put(numOfCar, result);
                    countDownLatch2.countDown();
                }
            });
        }
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

        System.out.println("Winner - " + winnerIndex + " time - " + results.get(winnerIndex));
        for (int key : results.keySet()) {
            System.out.println(key + " " + results.get(key));
        }
        
    }

    private static void sleepRandomTime(){
        long mills = (long)(Math.random() * 5000 + 1000);
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void prepare(int index){
        System.out.println(index + " start prepare");
        sleepRandomTime();
        System.out.println(index + " finish prepare");
        countDownLatch1.countDown();
    }

    private static void roadFirst(int index){
        System.out.println(index + " start roadFirst");
        sleepRandomTime();
        System.out.println(index + " finish roadFirst");
    }

    private static void roadSecond(int index){
        System.out.println(index + " start roadSecond");
        sleepRandomTime();
        System.out.println(index + " finish roadSecond");
    }

    private static void tunnel(int index){
        try {
            tunnelSemaphore.acquire();
            System.out.println(index + " start tunnel");
            sleepRandomTime();
            System.out.println(index + " finish tunnel");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            tunnelSemaphore.release();
        }
    }

}
