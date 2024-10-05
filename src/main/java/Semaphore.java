public class Semaphore {
    public static void main(String[] args) {

        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(3);

        for (int i = 0; i < 10; i++) {
            final int t = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();

                    System.out.println(name + t + "start working");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        semaphore.acquire();
                        WorkingWithFileSystem();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }

                    System.out.println(name + t +"finish working");
                }
            }).start();
        }

    }

    public static void WorkingWithFileSystem(){
        String name = Thread.currentThread().getName();

        System.out.println(name + "start working with file system");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(name + "finish working with file system");
    }

}
