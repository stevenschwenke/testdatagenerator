package de.stevenschwenke.java.testdatagenerator.testdatagenerator;

import com.google.common.collect.ImmutableMap;
import de.stevenschwenke.java.testdatagenerator.testdatagenerator.configs.TestDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

/**
 * This generator creates entities in the local database. The amount and characterization can be defined in configuration
 * files.
 * <p>
 * Running this generator multiple times is possible.
 */
@SpringBootApplication
@ActiveProfiles("local")
public class TestDataGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(TestDataGenerator.class);

    /**
     * Change this to other classes extending {@link TestDataConfig} to generate different sets of data.
     */
    private static final Class<?> DATA_CONFIG_CLASS = TestDataConfig.class;

    /**
     * Change this to generate data into different stages.
     */
    private static final Class<?> STAGE_CONFIG_CLASS = TestDataGeneratorConfigLocal.class;

    private static final Map<Class, String> MAP_OF_STAGE_CONFIG_CLASSES =
            ImmutableMap.of(
                    TestDataGeneratorConfigLocal.class, "local");

    @Autowired
    private EntityFactory entityFactory;

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) throws Exception {

        String profileName = MAP_OF_STAGE_CONFIG_CLASSES.get(STAGE_CONFIG_CLASS);
        LOG.info("Starting TestDataGenerator with profile: " + profileName);
        context = SpringApplication.run(TestDataGenerator.class, "--spring.profiles.active=" + profileName);

        TestDataGenerator generator = context.getBean(TestDataGenerator.class);
        generator.start();
    }

    private void start() throws Exception {

        setupAuditorUser();
        generateSpecifiedData();
        printReport();
        stop();
    }

    private void setupAuditorUser() {

        LOG.info("Setting auditor user ...");
//        sessionRepository.setUser("DUDE");
        LOG.info("Auditor user set successfully");
    }

    private void generateSpecifiedData() throws Exception {

        LOG.info("Generating specified data ...");
        TestDataConfig testDataConfig = (TestDataConfig) DATA_CONFIG_CLASS.getConstructor().newInstance();
        entityFactory.generateData(testDataConfig);
        LOG.info("Data generated successfully");
    }

    private void printReport() {

        LOG.info("Printing report ...");
        entityFactory.printReport();
        LOG.info("Report printed successfully");
    }

    private void stop() {
        LOG.info("Stopping TestDataGenerator ...");
        context.close();
    }
}
