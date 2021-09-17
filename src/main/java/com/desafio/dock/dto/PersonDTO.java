package com.desafio.dock.dto;

import com.desafio.dock.models.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PersonDTO {

    private Person person;

    public PersonDTO(){
        this.person = new Person();
    }

    public PersonDTO(Person person){
        this.person = person;
    }

    @JsonIgnore
    public Person getPerson(){
        return person;
    }

    public long getIdPerson(){
        return person.getIdPeople();
    }

    public String getName(){
        return person.getName();
    }

    public void setName(String name){
        person.getName();
    }

}
