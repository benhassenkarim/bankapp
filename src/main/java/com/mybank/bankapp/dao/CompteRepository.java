package com.mybank.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybank.bankapp.entities.Compte;
@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

	@Query("select o from Compte o where o.codeCompte=:x ")
	Compte findOne(@Param("x") String codeCpte);

	

	

}
