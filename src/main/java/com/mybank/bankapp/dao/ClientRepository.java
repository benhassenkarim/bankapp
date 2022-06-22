package com.mybank.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.bankapp.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

}
