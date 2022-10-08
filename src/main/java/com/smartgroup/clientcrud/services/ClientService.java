package com.smartgroup.clientcrud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.clientcrud.dto.ClientDTO;
import com.smartgroup.clientcrud.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<ClientDTO> clientsDTO = clientRepository.findAll().stream()
			.map(client -> new ClientDTO(client))
			.collect(Collectors.toList());
		
		return clientsDTO;
	}
	
}
