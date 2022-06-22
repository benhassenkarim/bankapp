package com.mybank.bankapp.metier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import com.mybank.bankapp.entities.Compte;
import com.mybank.bankapp.entities.Operation;

public interface IBanqueMetier {

	public Compte ConsulterCompte(String codeCpte);
	public void verser(String codeCpte,double montant);
	public void retirer(String codeCpte,double montant);
	public void virement(String codecpte1,String codecpte2,double montant);
	public Page<Operation> listOperations(String codeCpte,int page,int size);
	
}
