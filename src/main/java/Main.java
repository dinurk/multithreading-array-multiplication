import multithreadedArrayMultiplier.MultithreadedArrayMultiplier;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        double[] array1 = new double[100];
        double[] array2 = new double[100];
        double[] resultArray = new double[100];

        for(int i = 0; i < array1.length; i++) {
            array1[i] = random.nextDouble();
            array2[i] = random.nextDouble();
        }

        int maxThreadCount = 10;
        long[] elapsedTimes = new long[maxThreadCount];

        for(int threadCount = 1; threadCount <= maxThreadCount; threadCount++) {

            MultithreadedArrayMultiplier multiplier = new MultithreadedArrayMultiplier(threadCount);

            long startTime = System.currentTimeMillis();

            multiplier.multiply(array1, array2, resultArray);

            long endTime = System.currentTimeMillis();

            elapsedTimes[threadCount - 1] = endTime - startTime;
        }

        System.out.println("\n============================================================");
        System.out.println("Results:");
        System.out.println("thread count | elapsed time");
        for(int threadCount = 1; threadCount <= maxThreadCount; threadCount++) {
            System.out.println(String.format("%12d | %d", threadCount, elapsedTimes[threadCount - 1]));
        }
    }
}