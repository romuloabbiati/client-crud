package com.smartgroup.clientcrud.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<ClientDTO>> findAll(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
				
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				
		Page<ClientDTO> clientsDTO = clientService.findAllPaged(pageRequest);
		
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
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
