package com.smartgroup.clientcrud.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartgroup.clientcrud.entities.Client;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> clients = new ArrayList<>();
		Client client1 = new Client(1L, "Romulo", "012759046-37", 2000.0, null, 0);
		Client client2 = new Client(2L, "Isabela", "012345678-11", 2200.0, null, 0);
		
		clients.add(client1);
		clients.add(client2);
		
		return ResponseEntity.ok(clients);
	}
	
}
