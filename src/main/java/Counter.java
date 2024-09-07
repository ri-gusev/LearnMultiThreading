public class Counter {

    int value = 0;
    int value2 = 0;

    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    public void inc1(){
        synchronized (monitor1){
            value++;
        }
    }

    public void dic1(){
        synchronized (monitor1){
            value--;
        }
    }

    public int getValue1(){
        return value;
    }

    public void inc2(){
        synchronized (monitor2) {
            value2++;
        }
    }

    public void dic2(){
        synchronized (monitor2){
            value2--;
        }
    }

    public int getValue2(){
        return value2;
    }

}
