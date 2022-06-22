package com.mybank.bankapp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mybank.bankapp.dao.ClientRepository;
import com.mybank.bankapp.dao.CompteRepository;
import com.mybank.bankapp.dao.OperationRepository;
import com.mybank.bankapp.entities.Client;
import com.mybank.bankapp.entities.Compte;
import com.mybank.bankapp.entities.CompteCourant;
import com.mybank.bankapp.entities.CompteEpargne;
import com.mybank.bankapp.entities.Retrait;
import com.mybank.bankapp.entities.Versement;
import com.mybank.bankapp.metier.IBanqueMetier;

@SpringBootApplication
public class BankappApplication  implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(BankappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*Client c1=clientRepository.save(new Client("karim","karim@gmail.com"));
		Client c2=clientRepository.save(new Client("hosni","hosni@gmail.com"));
		
		Compte cp1= compteRepository.save(new CompteCourant("c1", new Date(), 9000, c1, 6000));
		Compte cp2= compteRepository.save(new CompteEpargne("c2", new Date(), 6000, c2, 5.5));
		
		operationRepository.save(new Versement(new Date(), 9000, cp1));
		operationRepository.save(new Versement(new Date(), 6000, cp1));
		operationRepository.save(new Versement(new Date(), 2300, cp1));
		operationRepository.save(new Retrait(new Date(), 9000, cp1));
		
		operationRepository.save(new Versement(new Date(), 5000, cp2));
		operationRepository.save(new Versement(new Date(), 4000, cp2));
		operationRepository.save(new Versement(new Date(), 200, cp2));
		operationRepository.save(new Retrait(new Date(), 3000, cp2));
		
		banqueMetier.verser("c1", 111111);*/
	}

}
