package com.example.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.api.domain.Adress;
import com.example.api.repository.AdressRepository;

public class AdressService {
	
	
	
	@Autowired
	private AdressRepository repository;

	public Optional<Adress> findById(Long id) {
		return repository.findById(id);
	}

	public ResponseEntity<Adress> createAdress(Adress adress) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(adress));

	}

	public ResponseEntity<Adress> editAdress(Adress adress) {

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(adress));

	}

	public void deleteAdress(Long id) {
		repository.deleteById(id);
	}

}
