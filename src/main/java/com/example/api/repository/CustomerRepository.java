package com.example.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.api.domain.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findAllByOrderByNameAsc();
	
	// vai ser usado para verificação se existe um cliente cadastrado.
	public Optional<Customer> findByEmail(String email);

}
