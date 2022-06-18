package com.example.api.web.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;
import com.example.api.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@Autowired
	private AddressRepository repository;

	
// retornando todos os endereços.
	@GetMapping
	public ResponseEntity<List<Address>> getAll (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	
	//retornando endereço pelo id, fazendo a verificação de existencia atraves da class AddressService.
	@GetMapping("/{id}")
	public Optional<Address> findById(@PathVariable Long id) {
		return Optional.ofNullable(service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
	}
	
	
	//criando endereço atraves da classe AddressService, que verifica se existe um usuario para cadastrar o endereço
	@PostMapping
	public ResponseEntity<Address> createAdress(@Valid @RequestBody Address address) {
		return service.createAdress(address);
	}

	//editando o endereço atraves da classe AddressService. verificando se existe o endereço para ser alterado.
	@PutMapping
	public ResponseEntity<Address> editAdress(@Valid @RequestBody Address address) {
		return service.editAdress(address);
	}

	//deletando o endereço atravez da classe AddressService, fazendo verificação se existe o endereço para ser deletado
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  Delete(@PathVariable Long id) {
		return service.deleteAdress(id);
	}

}
