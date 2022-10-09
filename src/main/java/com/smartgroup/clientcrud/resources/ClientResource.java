package com.smartgroup.clientcrud.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO clientDTO = clientService.findById(id);
		return ResponseEntity.ok(clientDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
		clientDTO = clientService.insert(clientDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(clientDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(clientDTO);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
		clientDTO = clientService.update(id, clientDTO);
		return ResponseEntity.ok(clientDTO);
	}
	
}
