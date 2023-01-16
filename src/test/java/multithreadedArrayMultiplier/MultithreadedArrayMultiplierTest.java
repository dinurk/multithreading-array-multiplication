package multithreadedArrayMultiplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultithreadedArrayMultiplierTest {

    @Test
    @DisplayName("multiply with valid input test")
    void multiplyWithValidInput() {

        double[] array1 = new double[100];
        double[] array2 = new double[100];
        double[] resultArray = new double[100];

        for(int i = 0; i < 100; i++) {
            array1[i] = 3;
            array2[i] = 2;
        }

        MultithreadedArrayMultiplier multiplier = new MultithreadedArrayMultiplier(4);
        multiplier.multiply(array1, array2, resultArray);

        for(double value : resultArray) {
            assertEquals(6.0, value);
        }
    }

    @Test
    @DisplayName("multiply with invalid input test")
    void multiplyWithInvalidInput() {

        MultithreadedArrayMultiplier multiplier = new MultithreadedArrayMultiplier(4);

        assertThrows(IllegalArgumentException.class, () -> multiplier.multiply(new double[50], new double[100], new double[100]));
        assertThrows(IllegalArgumentException.class, () -> multiplier.multiply(new double[100], new double[50], new double[100]));
        assertThrows(IllegalArgumentException.class, () -> multiplier.multiply(new double[100], new double[100], new double[50]));
        assertThrows(IllegalArgumentException.class, () -> multiplier.multiply(new double[50], new double[50], new double[100]));
        assertThrows(IllegalArgumentException.class, () -> multiplier.multiply(new double[100], new double[50], new double[50]));
        assertThrows(IllegalArgumentException.class, () -> multiplier.multiply(new double[100], new double[50], new double[10]));
    }
}
