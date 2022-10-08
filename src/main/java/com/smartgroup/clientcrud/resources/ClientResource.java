package com.smartgroup.clientcrud.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartgroup.clientcrud.entities.Client;
import com.smartgroup.clientcrud.services.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> clients = clientService.findAll();
		
		return ResponseEntity.ok(clients);
	}
	
}
