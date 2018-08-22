package de.stevenschwenke.java.testdatagenerator.address;

import de.stevenschwenke.java.testdatagenerator.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends JpaRepository<Address, Long> {

}
