package com.smartgroup.clientcrud.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartgroup.clientcrud.dto.ClientDTO;
import com.smartgroup.clientcrud.services.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<ClientDTO> clientsDTO = clientService.findAll();
		
		return ResponseEntity.ok(clientsDTO);
	}
	
}
