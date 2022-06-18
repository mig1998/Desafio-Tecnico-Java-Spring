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

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	//retornando todos clientes registrados.
	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	
	//retornando o cliente  se ele existir através da classe CustomerService
	@GetMapping("/{id}")
	public Optional<Customer> findById(@PathVariable Long id) {
		return Optional.ofNullable(service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
	}

	//cadastro de cliente e fazendo a verificação se já existe através da classe CustomerService
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		return service.createCustomer(customer)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.IM_USED).build());
	}

	//editando o cliente atraves da classe CustomerService e fazendo verificação se ele existe.
	@PutMapping
	public ResponseEntity<Customer> editCustomer(@Valid @RequestBody Customer customer) {
		return service.editCustomer(customer);
	}

	//deletando o cliente atraves da classe CustomerService e verificando se ele existe.
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  Delete(@PathVariable Long id) {
		return service.deleteCustomer(id);
	}
}
