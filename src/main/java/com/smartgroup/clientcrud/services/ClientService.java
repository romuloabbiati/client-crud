package com.smartgroup.clientcrud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.clientcrud.dto.ClientDTO;
import com.smartgroup.clientcrud.entities.Client;
import com.smartgroup.clientcrud.repositories.ClientRepository;
import com.smartgroup.clientcrud.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		return clientRepository.findAll().stream()
			.map(client -> new ClientDTO(client))
			.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> clientOptional = clientRepository.findById(id);
		
		Client client = clientOptional.orElseThrow(
				() -> new ResourceNotFoundException("Client not found!"));
		
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		Client client = new Client();
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpf());
		client.setIncome(clientDTO.getIncome());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
		
		client = clientRepository.save(client);
		
		return new ClientDTO(client);
	}
	
}
