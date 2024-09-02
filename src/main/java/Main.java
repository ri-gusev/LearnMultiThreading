import java.util.Arrays;

public class Main {

    private static final int SIZE = 100_000_000;
    private static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        withConcurrency();
        withOutConcurrency();
    }

    private static void withConcurrency(){
        float[] nums = new float[SIZE];
        Arrays.fill(nums, 1f);

        long before = System.currentTimeMillis();

        float[] nums1 = new float[HALF];
        float[] nums2 = new float[HALF];
        System.arraycopy(nums,0, nums1,0,HALF);
        System.arraycopy(nums,HALF, nums2,0,HALF);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    float f = (float) i;
                    nums1[i] = (float) (nums1[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    float f = (float) i;
                    nums2[i] = (float) (nums2[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(nums1,0,nums,0,HALF);
        System.arraycopy(nums2,0,nums,HALF,HALF);

        long after = System.currentTimeMillis();

        System.out.println("WithConcurrency " + (after-before));
    }


    private static void withOutConcurrency(){
        float[] nums = new float[SIZE];
        Arrays.fill(nums, 1f);

        long before = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            float f = (float) i;
            nums[i] = (float) (nums[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
        }
        long after = System.currentTimeMillis();

        System.out.println("WithOutConcurrency " + (after-before));
    }
}
