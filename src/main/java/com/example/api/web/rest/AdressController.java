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

import com.example.api.domain.Adress;
import com.example.api.service.AdressService;

@RestController
@RequestMapping("/adress")

public class AdressController {

	@Autowired
	private AdressService service;

	@PostMapping
	public ResponseEntity<Adress> createAdress(@Valid @RequestBody Adress adress) {
		return service.createAdress(adress);
	}

	@PutMapping
	public ResponseEntity<Adress> editAdress(@Valid @RequestBody Adress adress) {
		return service.editAdress(adress);
	}

	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Long id) {
		service.deleteAdress(id);
	}

}
