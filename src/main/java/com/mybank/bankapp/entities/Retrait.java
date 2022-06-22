package com.mybank.bankapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("r")
public class Retrait extends Operation {

	public Retrait() {
		super();
		
	}

	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		
	}

	
}
