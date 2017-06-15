package com.ddv.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Transactional
public class Application {

	@Autowired
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
/*
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
            log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
            log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
            log.info("");
            return null;
	}
*/
	
	@Bean
	public CommandLineRunner demo2(LegalFundRepository lfRepository, ShareClassRepository scRepository) {
		log.info("Legal funds and Share classes:");
		LegalFund lf1 = new LegalFund("LAB1", "LF_1", "LegalFund1");
		LegalFund lf2 = new LegalFund("LAB1", "LF_2", "LegalFund2");
		LegalFund lf3 = new LegalFund("LAB2", "LF_3", "LegalFund3");
		
		ShareClass sc1 = new ShareClass("LAB1", "SC_1", "041", "ShareClass1", lf1);
		ShareClass sc2 = new ShareClass("LAB1", "SC_2", "042", "ShareClass2", lf1);
		ShareClass sc3 = new ShareClass("LAB1", "SC_3", "043", "ShareClass3", lf1);
		ShareClass sc4 = new ShareClass("LAB2", "SC_4", "044", "ShareClass4", lf3);

		lfRepository.saveAndFlush(lf1);
		lfRepository.saveAndFlush(lf2);
		lfRepository.saveAndFlush(lf3);
		scRepository.saveAndFlush(sc1);
		scRepository.saveAndFlush(sc2);
		scRepository.saveAndFlush(sc3);
		scRepository.saveAndFlush(sc4);

		entityManager.clear();
		
		log.info("##################################################################################");
		
		List<LegalFund> legalFunds = lfRepository.findAll(new LegalFundSpecification());
//		List<LegalFund> legalFunds = lfRepository.findAll();
		for (LegalFund legalFund : legalFunds) {
			log.info("Found " + legalFund);
		}
		
		return null;
	}

	
}