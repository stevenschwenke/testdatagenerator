package de.stevenschwenke.java.testdatagenerator.testdatagenerator.randoms;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * To test an application's calculating algorithms, random numbers can be necessary. However, these numbers have to be
 * deterministic when called multiple times. That's why simply calling Math.random() is not enough. Instead, this
 * class provides pseudo-random numbers.
 */
public class PseudoRandoms {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PseudoRandoms.class);

    private List<Double> pseudoRandomDoubles = new ArrayList<>();
    private int pointer = 0;

    public PseudoRandoms() {

        try (Stream<String> lines = Files.lines(Paths.get("random_numbers.txt"));) {
            List<String> randomsAsString = lines.collect(Collectors.toList());
            Stream<Double> randomsAsDouble = randomsAsString.stream().map(Double::valueOf);
            randomsAsDouble.forEach(pseudoRandomDoubles::add);

        } catch (IOException e) {
            LOG.error("Initializing of PseudoRandom-Generator failed.", e);
        }
    }

    public Double randomDouble() {

        if (pointer == pseudoRandomDoubles.size() - 1) {
            pointer = 0;
        } else {
            pointer++;
        }

        return pseudoRandomDoubles.get(pointer);
    }

    boolean rolloverJustHappened() {
        return pointer == 0;
    }
}
