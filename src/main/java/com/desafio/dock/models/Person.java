package com.desafio.dock.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPerson;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String cpf;

    @Column
    private Date date;

    public Person(){

    }

    public Person(String name, String cpf, Date date) {
        this.name = name;
        this.cpf = cpf;
        this.date = date;
    }

    public long getIdPeople() {
        return idPerson;
    }

    public void setIdPeople(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idPerson == person.idPerson;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson);
    }
}
