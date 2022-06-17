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

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public ResponseEntity<Customer> createCustomer(Customer customer) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(customer));

	}

	public ResponseEntity<Customer> editCustomer(Customer customer) {

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(customer));

	}

	public void deleteCustomer(Long id) {
		repository.deleteById(id);
	}

}
