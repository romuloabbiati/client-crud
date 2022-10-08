package com.smartgroup.clientcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartgroup.clientcrud.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
