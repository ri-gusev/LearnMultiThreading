
public class Main {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Printer.print(15);
                Printer.print(1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Printer.scan(15);
                Printer.scan(1);
            }
        }).start();
    }
}
