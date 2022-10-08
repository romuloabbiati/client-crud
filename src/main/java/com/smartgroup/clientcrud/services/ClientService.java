package com.smartgroup.clientcrud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.clientcrud.entities.Client;
import com.smartgroup.clientcrud.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
}
