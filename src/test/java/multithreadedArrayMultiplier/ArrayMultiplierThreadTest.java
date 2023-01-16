package multithreadedArrayMultiplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayMultiplierThreadTest {

    @Test
    @DisplayName("input validation test")
    void inputValidationTest() {

        int negativeIndex = -1;
        int valueEqualToArrayLength = 5;
        int valueGreaterThanArrayLength = 6;

        assertThrows(IllegalArgumentException.class,
                 () -> new ArrayMultiplierThread(new double[5],
                                                 new double[5],
                                                 new double[5],
                                            negativeIndex, 4));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayMultiplierThread(new double[5],
                        new double[5],
                        new double[5],
                        0, negativeIndex));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayMultiplierThread(new double[5],
                        new double[5],
                        new double[5],
                        0, valueEqualToArrayLength));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayMultiplierThread(new double[5],
                        new double[5],
                        new double[5],
                        0, valueGreaterThanArrayLength));

        // from > to
        assertThrows(IllegalArgumentException.class,
                () -> new ArrayMultiplierThread(new double[5],
                        new double[5],
                        new double[5],
                        5, 1));
    }
}
