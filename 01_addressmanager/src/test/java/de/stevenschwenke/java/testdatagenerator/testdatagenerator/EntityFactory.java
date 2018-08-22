package de.stevenschwenke.java.testdatagenerator.testdatagenerator;

import de.stevenschwenke.java.testdatagenerator.person.Person;
import de.stevenschwenke.java.testdatagenerator.person.PersonRepository;
import de.stevenschwenke.java.testdatagenerator.testdatagenerator.configs.TestDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EntityFactory {

    private static final Logger LOG = LoggerFactory.getLogger(EntityFactory.class);

    private PersonRepository personRepository;

    @Autowired
    public EntityFactory(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void generateData(TestDataConfig dataConfig) {

        generateIdeas(dataConfig.getAmountPersons());

    }

    private void generateIdeas(int amountPersons) {
        for (int i = 0; i < amountPersons; i++) {
            Person newPerson = new Person();
            personRepository.save(newPerson);
        }
    }

    public void printReport() {
        LOG.info("Database Report:");
        LOG.info(personRepository.count() + " persons");
    }
}
