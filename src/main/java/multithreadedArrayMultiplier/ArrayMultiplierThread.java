package multithreadedArrayMultiplier;

class ArrayMultiplierThread extends Thread {

    private final double[] array1, array2, resultArray;
    private final int from, to;

    ArrayMultiplierThread(double[] array1, double[] array2, double[] resultArray, int from, int to) {
        System.out.printf("thread created, multiplying from %d to %d%n", from, to);

        if(from < 0) {
            throw new IllegalArgumentException(String.format("from argument value must be greater than or equal to 0, from = %d given", from));
        }

        if(to < 0) {
            throw new IllegalArgumentException(String.format("to argument value must be greater than or equal to 0, to = %d given", to));
        }

        if(to >= array1.length) {
            throw new IllegalArgumentException(String.format("to argument value must be less (<) than array.length, to = %d and array.length = %d given", to, array1.length));
        }

        if(from > to) {
            throw new IllegalArgumentException(String.format("from argument value must be less (<) than to argument value, from = %d and to = %d given", from, to));
        }

        this.array1 = array1;
        this.array2 = array2;
        this.resultArray = resultArray;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for(int i = from; i <= to; i++) {
            resultArray[i] = array1[i] * array2[i];
        }
    }
}
