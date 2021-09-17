package com.desafio.dock.services.person;

import com.desafio.dock.models.Person;
import java.util.*;

public interface PersonService {
    Person createPerson(Person person);
    Person getPersonByID(long id);
    List<Person> getAll();
}
