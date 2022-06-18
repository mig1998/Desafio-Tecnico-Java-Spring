package com.example.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	
	//retornar endereço pelo Id
	public Optional<Address> findById(Long id) {
		return addressRepository.findById(id);
	}

	
	//adicionando endereço somente se existir um usuario
	public ResponseEntity<Address> createAdress(Address address) {
		if (customerRepository.existsById(address.getCustomer().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(addressRepository.save(address));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	
	//editando endereço apenas se existir um usuario
	public ResponseEntity<Address> editAdress(Address address) {
		
		if (addressRepository.existsById(address.getId())) {
		return ResponseEntity.status(HttpStatus.OK).body(addressRepository.save(address));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	
	}

	//deletando um endereço somente se ele existir
	public ResponseEntity<?> deleteAdress(Long id) {

		return addressRepository.findById(id).map(resposta -> {
			addressRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}).orElse(ResponseEntity.notFound().build());

	}

}
