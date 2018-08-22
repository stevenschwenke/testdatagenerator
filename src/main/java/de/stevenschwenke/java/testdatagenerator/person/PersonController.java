package de.stevenschwenke.java.testdatagenerator.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

}
