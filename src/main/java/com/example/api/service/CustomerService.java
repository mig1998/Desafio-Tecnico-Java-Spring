package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	
	//retorna lista completa de clientes
	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	//retorna cliente pelo id
	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	
	//verificando se ja existe um cliente cadastrado, se não tiver, o cadastrado é realizado
	public Optional<Customer> createCustomer(Customer customer) {
		
		if (repository.findByEmail(customer.getEmail()).isPresent())
			return Optional.empty();
		
		
		return Optional.of(repository.save(customer));

	}

	//editando cliente apenas se ele existir
	public ResponseEntity<Customer> editCustomer(Customer customer) {

		if (repository.existsById(customer.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(customer));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	//deletando usuario apenas se ele existir
	public ResponseEntity<?> deleteCustomer(Long id) {

		return repository.findById(id).map(resposta -> {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}).orElse(ResponseEntity.notFound().build());

	}

}
