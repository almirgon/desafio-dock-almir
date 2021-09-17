package com.desafio.dock;

import com.desafio.dock.models.Person;
import com.desafio.dock.repositories.PersonRepository;
import com.desafio.dock.services.person.PersonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class DockApplication {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {

		SpringApplication.run(DockApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			String name = "Almir Crispiniano";
			String cpf = "18173410348";
			Date date = new Date();
			Person p = new Person(name, cpf, date);
			personRepository.save(p);

		};
	}

}
