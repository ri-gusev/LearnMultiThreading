public class Main {
    public static void main(String[] args) {
        System.out.println("Start");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    System.out.print(i + " ");
                }
            }
        });
        thread.start();

        for (int i = 0; i < 100; i++){
            System.out.print("M");
        }
        System.out.println("\nFinish");
    }
}
