package com.desafio.dock.services.person;

import com.desafio.dock.exceptions.EntityNotExistsException;
import com.desafio.dock.models.Person;
import com.desafio.dock.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonBean implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        Date data = new Date();
        person.setDate(data);
        return this.personRepository.save(person);
    }

    @Override
    public Person getPersonByID(long id) {
        Person person = this.personRepository.findPersonByIdPerson(id);
        if(person == null){
            throw new EntityNotExistsException("Usuário não existe");
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }
}
