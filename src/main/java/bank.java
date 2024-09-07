public class bank {

    private static int CAPITAL = 10_000_000;

    public synchronized static void getMoney(String name, int money){
        System.out.println(name + " подошел к банкомату");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (CAPITAL >= money){
            CAPITAL = CAPITAL-money;
            System.out.println(name + " вывел " + money + " рублей");
        }else System.out.println("В банкомате недостаточно денег для " + name);

    }

    public static int getCAPITAL() {
        return CAPITAL;
    }
}
