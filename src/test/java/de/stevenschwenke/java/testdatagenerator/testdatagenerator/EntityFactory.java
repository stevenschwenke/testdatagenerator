package de.stevenschwenke.java.testdatagenerator.testdatagenerator;

import de.stevenschwenke.java.testdatagenerator.address.Address;
import de.stevenschwenke.java.testdatagenerator.address.AddressRepository;
import de.stevenschwenke.java.testdatagenerator.person.Person;
import de.stevenschwenke.java.testdatagenerator.person.PersonRepository;
import de.stevenschwenke.java.testdatagenerator.testdatagenerator.configs.TestDataConfig;
import de.stevenschwenke.java.testdatagenerator.testdatagenerator.randoms.PseudoRandoms;
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
    private AddressRepository addressRepository;

    @Autowired
    public EntityFactory(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public void generateData(TestDataConfig dataConfig, PseudoRandoms pseudoRandoms) {

        generateAddresses(dataConfig.getAmountAddresses(), pseudoRandoms);
        generatePersons(dataConfig.getAmountPersons());

    }

    private void generateAddresses(int amountAddresses, PseudoRandoms pseudoRandoms) {
        for (int i = 0; i < amountAddresses; i++) {
            Address newAddress = new Address("City" + i, "Street" + i, (long) (pseudoRandoms.randomDouble() * 100));
            addressRepository.save(newAddress);
        }
    }

    private void generatePersons(int amountPersons) {
        for (int i = 0; i < amountPersons; i++) {
            Person newPerson = new Person("Android" + i);

            long amountAddresses = addressRepository.count();
            Address address = addressRepository.findAll().get((int) (Math.random() * amountAddresses));
            newPerson.setAddress(address);

            personRepository.save(newPerson);
        }
    }

    public void printReport() {
        LOG.info("Database Report:");
        LOG.info(personRepository.count() + " persons");
        LOG.info(addressRepository.count() + " addresses");
    }
}
