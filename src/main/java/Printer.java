import javax.management.monitor.Monitor;

public class Printer {

    private static final Object monitor1 = new Object();
    private static final Object monitor2 = new Object();

    public static void print(int pages){
        synchronized (monitor1){
            try {
                for (int j = 0; j < 3; j++) {
                    Thread.sleep(900);
                }
                for (int i = 1; i < pages+1; i++) {
                    System.out.println("Отпечатано " + i +"стр");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void scan(int pages){
        synchronized (monitor2){
            try {
                for (int j = 0; j < 3; j++) {
                    Thread.sleep(900);
                }
                for (int i = 1; i < pages+1; i++) {
                    System.out.println("Отсканировано " + i +"стр");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
