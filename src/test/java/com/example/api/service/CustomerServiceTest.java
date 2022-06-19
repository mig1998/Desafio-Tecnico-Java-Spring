package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CustomerServiceTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	

	@Autowired
	private CustomerRepository repository;

	
	@BeforeAll
	void start() {
		repository.deleteAll();
	}

	@Test
	@DisplayName("Cadastrar cliente")

	public void criarCliente() {
		HttpEntity<Customer> corpoRequisicao = new HttpEntity<Customer>(new Customer(0L, "Paulo Antunes",
				"paulo_antunes@email.com.br"));

		ResponseEntity<Customer> corpoResposta = testRestTemplate.exchange("/customers/", HttpMethod.POST,
				corpoRequisicao, Customer.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

		assertEquals(corpoRequisicao.getBody().getName(), corpoResposta.getBody().getName());

		assertEquals(corpoRequisicao.getBody().getEmail(), corpoResposta.getBody().getEmail());
	}
	
	
	
}
