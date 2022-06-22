package com.mybank.bankapp.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.bankapp.dao.ClientRepository;
import com.mybank.bankapp.dao.CompteRepository;
import com.mybank.bankapp.dao.OperationRepository;
import com.mybank.bankapp.entities.Compte;
import com.mybank.bankapp.entities.CompteCourant;
import com.mybank.bankapp.entities.Operation;
import com.mybank.bankapp.entities.Retrait;
import com.mybank.bankapp.entities.Versement;
@Service
@Transactional
public class BanqueMetierIpml implements IBanqueMetier {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Override
	public Compte ConsulterCompte(String codeCpte) {
		Compte cp=compteRepository.findOne(codeCpte);
		if(cp==null)throw new RuntimeException("compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp=ConsulterCompte(codeCpte);
		Versement v= new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp=ConsulterCompte(codeCpte);
		double facilitescaisse=0;
		if(cp instanceof CompteCourant)
		  {facilitescaisse=((CompteCourant) cp).getDecouvert();}
		if (cp.getSolde()+facilitescaisse<montant)
		   {throw new RuntimeException("solde insuffisant");}
		Retrait r= new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codecpte1, String codecpte2, double montant) {
		if(codecpte1.equals(codecpte2)) {
			throw new RuntimeException("Impossible Virement dans le meme compte");
		}
		retirer(codecpte1, montant);
		verser(codecpte2, montant);
		
	}

	@Override
	public Page<Operation> listOperations(String codeCpte, int page, int size) {
		
		return operationRepository.listOperation(codeCpte, PageRequest.of(page, size));
	}

	

	
}
