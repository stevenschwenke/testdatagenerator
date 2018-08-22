package de.stevenschwenke.java.testdatagenerator.testdatagenerator;

import de.stevenschwenke.java.testdatagenerator.conf.DatabaseConfigDev;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@Configuration
@Import(value = {DatabaseConfigDev.class})
@ComponentScan(basePackages = "de.stevenschwenke.java.testdatagenerator")
public class TestDataGeneratorConfigLocal {

}
