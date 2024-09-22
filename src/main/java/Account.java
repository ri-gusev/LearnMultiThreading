public class Account {

    private final Object firstMonitor = new Object();
    private final Object secondMonitor = new Object();
    private int firstAmount;
    private int secondAmount;

    public Account(int secondAmount, int firstAmount) {
        this.secondAmount = secondAmount;
        this.firstAmount = firstAmount;
    }

    public void transfer1To2(int money){
        synchronized (firstMonitor){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (money <= firstAmount){
                System.out.println("money <= firstAmount");
                synchronized (secondMonitor) {
                    firstAmount -= money;
                    secondAmount += money;
                }
            }else {
                System.out.println("Insufficient funds");
            }
        }
    }

    public void transfer2To1(int money){
        synchronized (secondMonitor){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (money <= secondAmount){
                System.out.println("money <= secondAmount");
                synchronized (firstMonitor){
                    secondAmount -= money;
                    firstAmount += money;
                }
            }else {
                System.out.println("Insufficient funds");
            }
        }
    }

}
