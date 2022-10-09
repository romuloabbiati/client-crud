package com.smartgroup.clientcrud.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.clientcrud.dto.ClientDTO;
import com.smartgroup.clientcrud.entities.Client;
import com.smartgroup.clientcrud.repositories.ClientRepository;
import com.smartgroup.clientcrud.services.exceptions.DatabaseException;
import com.smartgroup.clientcrud.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		return clientRepository.findAll(pageRequest)
			.map(client -> new ClientDTO(client));
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
		clientDTO = copyClientDTOToClient(client, clientDTO);
		
		client = clientRepository.save(client);
		
		return clientDTO;
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client client = clientRepository.getOne(id);
			
			clientDTO = copyClientDTOToClient(client, clientDTO);
			
			client = clientRepository.save(client);
			
			return clientDTO;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private ClientDTO copyClientDTOToClient(Client client, ClientDTO clientDTO) {
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpf());
		client.setIncome(clientDTO.getIncome());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
		
		return new ClientDTO(client);
	}
	
	
}
