import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Thread client1 = new Thread(new Runnable() {
            @Override
            public void run() {
                bank.getMoney("Roman", 5_000_000);
            }
        });

        Thread client2 = new Thread(new Runnable() {
            @Override
            public void run() {
                bank.getMoney("Igor", 5_000_000);
            }
        });

        Thread client3 = new Thread(new Runnable() {
            @Override
            public void run() {
                bank.getMoney("Ivan", 5_000_000);
            }
        });

        client1.start();
        client2.start();
        client3.start();

        try {
            client3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bank.getCAPITAL());

    }
}
