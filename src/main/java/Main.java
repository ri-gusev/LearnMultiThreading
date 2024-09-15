import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3,new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.print(".");
                        Thread.sleep(400);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                
            }
        });

        Future<String> futureName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "John";
            }
        });

        Future<Integer> futureAge = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 18;
            }
        });

        try {
            String name = futureName.get();
            int age = futureAge.get();
            System.out.println("\n" + name + " " + age);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        
    }
}
