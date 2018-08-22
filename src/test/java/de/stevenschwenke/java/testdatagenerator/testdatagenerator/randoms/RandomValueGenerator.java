package de.stevenschwenke.java.testdatagenerator.testdatagenerator.randoms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generates random numbers and writes them into a file. These are used by {@link PseudoRandoms} to provide
 * pseudo-randomized numbers.
 * <p>
 * If the generated file is pushed to source control, this generator doesn't need to be executed.
 */
public class RandomValueGenerator {

    private static final int AMOUNT_OF_RANDOM_VALUES = 1000;

    public static void main(String[] args) throws IOException {
        new RandomValueGenerator().generateRandomValuesToFile();
    }

    private void generateRandomValuesToFile() throws IOException {
        List<String> randomNumbersAsString = Stream.generate(Math::random)
                .limit(AMOUNT_OF_RANDOM_VALUES)
                .map(Object::toString)
                .collect(Collectors.toList());
        Files.write(Paths.get("random_numbers.txt"), randomNumbersAsString);
    }
}
