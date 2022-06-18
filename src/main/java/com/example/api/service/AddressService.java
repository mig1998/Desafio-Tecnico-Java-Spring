package com.example.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;

	
	public Optional<Address> findById(Long id) {
		return repository.findById(id);
	}

	
	public ResponseEntity<Address> createAdress(Address adress) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(adress));

	}

	public ResponseEntity<Address> editAdress(Address adress) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(adress));
	}

	public void deleteAdress(Long id) {
		repository.deleteById(id);
	}

}
