package de.stevenschwenke.java.testdatagenerator.testdatagenerator.randoms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PseudoRandomsTest {

    @Test
    void rolloverAfter1000Numbers() {

        // Initialize
        PseudoRandoms pseudoRandoms = new PseudoRandoms();

        for (int counter = 0; counter < 1000; counter++) {
            pseudoRandoms.randomDouble();
        }

        assertTrue(pseudoRandoms.rolloverJustHappened());
    }

    @Test
    void afterRolloverTheSameNumbersAreGenerated() {

        // Initialize
        PseudoRandoms pseudoRandoms = new PseudoRandoms();

        Double firstDouble = null;
        Double secondDouble = null;
        Double thirdDouble = null;

        for (int counter = 0; counter < 1000; counter++) {
            Double randomDouble = pseudoRandoms.randomDouble();
            if (counter == 0) {
                firstDouble = randomDouble;
            }
            if (counter == 1) {
                secondDouble = randomDouble;
            }
            if (counter == 2) {
                thirdDouble = randomDouble;
            }
        }

        assertEquals(firstDouble, pseudoRandoms.randomDouble());
        assertEquals(secondDouble, pseudoRandoms.randomDouble());
        assertEquals(thirdDouble, pseudoRandoms.randomDouble());
    }
}