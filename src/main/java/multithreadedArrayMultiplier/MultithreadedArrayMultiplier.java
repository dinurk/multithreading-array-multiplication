package multithreadedArrayMultiplier;

public class MultithreadedArrayMultiplier {

    private final int threadCount;
    private final ArrayMultiplierThread[] threads;

    public MultithreadedArrayMultiplier(int threadCount) {

        if(threadCount < 0) {
            throw new IllegalArgumentException("threadCount must be positive integer");
        }
        this.threadCount = threadCount;
        threads = new ArrayMultiplierThread[threadCount];
    }

    public void multiply(double[] array1, double[] array2, double[] resultArray) {

        System.out.println("------------------------------------------------------------");
        System.out.printf("Begin multiplying arrays, threadCount = %d\n\n", threadCount);

        if(array1.length != array2.length || array2.length != resultArray.length) {
            throw new IllegalArgumentException("array1, array2 and resultArray mush have the same length");
        }

        int arrayLength = array1.length;
        int chunkSize = arrayLength / threadCount;

        int unallocatedElementsCount = arrayLength - threadCount * chunkSize;

        if(unallocatedElementsCount == 0) {
            for(int i = 0; i < threadCount; i++) {
                int from = i * chunkSize;
                int to = (i + 1) * chunkSize - 1;
                threads[i] = new ArrayMultiplierThread(array1, array2, resultArray, from, to);
            }
        }
        else if(unallocatedElementsCount == 1) {
            for(int i = 0; i < threadCount; i++) {
                int from = i * chunkSize;
                int to = (i + 1) * chunkSize - 1;
                if(i == threadCount - 1) to += 1;
                threads[i] = new ArrayMultiplierThread(array1, array2, resultArray, from, to);
            }
        }
        else {
            for (int i = 0; i < threadCount - 1; i++) {
                int from = i * chunkSize;
                int to = (i + 1) * chunkSize - 1;
                threads[i] = new ArrayMultiplierThread(array1, array2, resultArray, from, to);
            }

            int elementsToAllocateCount = arrayLength - (threadCount - 1) * chunkSize;

            threads[threadCount - 2] = new ArrayMultiplierThread(array1, array2, resultArray,
                    (threadCount - 1) * chunkSize,
                    (threadCount - 1) * chunkSize + elementsToAllocateCount / 2 - 1);

            threads[threadCount - 1] = new ArrayMultiplierThread(array1, array2, resultArray,
                    (threadCount - 1) * chunkSize + elementsToAllocateCount / 2,
                    (threadCount - 1) * chunkSize + elementsToAllocateCount - 1);
        }

        for(Thread thread : threads) {
            thread.start();
        }

        try {
            for(Thread thread : threads) thread.join();
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
