package de.stevenschwenke.java.testdatagenerator.testdatagenerator.configs;

/**
 * Configuration test data. Each method is designed to be overriden.
 */
public class TestDataConfig {

    public boolean generateDeterministicDesignations() {
        return false;
    }

    public int getAmountPersons() {
        return 4;
    }

    public int getAmountAddresses() {
        return 4;
    }
}
