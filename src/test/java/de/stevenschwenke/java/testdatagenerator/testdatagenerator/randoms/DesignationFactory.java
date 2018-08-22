package de.stevenschwenke.java.testdatagenerator.testdatagenerator.randoms;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Factory for creating random designations with Java Faker.
 */
public class DesignationFactory {

    /**
     * true, wenn die vom Generator erzeugten Entitaeten auch bei mehrfacher Ausfuehrung deterministische
     * Bezeichnungen haben sollen. Dies ist z.B. zum Testen von Importfunktionen sinnvoll, damit die zu
     * importierende Datei nur einmal auf die vom Generator erstellten Entitaeten angepasst werden muss.
     */
    private boolean deterministicDesignations;

    private Faker faker = new Faker(new Locale(Locale.GERMAN.getLanguage()));

    public DesignationFactory(boolean deterministicDesignations) {
        this.deterministicDesignations = deterministicDesignations;
    }

    public String createCityName(int idx) {
        return deterministicDesignations ?
                "city_" + idx :
                StringUtils.abbreviate(faker.address().city(), 40) + " (" + idx + ")";
    }

    public String createStreetName(int idx) {
        return deterministicDesignations ?
                "street_" + idx :
                StringUtils.abbreviate(faker.address().streetName(), 40) + " (" + idx + ")";
    }

    public String createPersonName(int idx) {
        return deterministicDesignations ?
                "Max Random_" + idx :
                StringUtils.abbreviate(faker.superhero().name(), 40) + " (" + idx + ")";
    }
}
