package com.example.api.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Address;
import com.example.api.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping
	public ResponseEntity<Address> createAdress(@Valid @RequestBody Address address) {
		return service.createAdress(address);
	}

	@PutMapping
	public ResponseEntity<Address> editAdress(@Valid @RequestBody Address address) {
		return service.editAdress(address);
	}

	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Long id) {
		service.deleteAdress(id);
	}

}
