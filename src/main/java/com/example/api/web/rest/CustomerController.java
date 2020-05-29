package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.domain.exception.BusinessException;
import com.example.api.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public Page<Customer> findAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		if (page == null || size == null) {
			return service.findAll(0, 10); // TODO ajustar para listAll
		} else {
			return service.findAll(page, size);
		}
	}

	@PostMapping
	public ResponseEntity<Customer> create(Customer customer) throws BusinessException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customer));
	}

	@PutMapping
	public Customer update(Customer customer) throws BusinessException {
		return service.save(customer);
	}
	
	@DeleteMapping
	public Customer delete(Customer customer){
		service.delete(customer);
		return customer;
	}
	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@GetMapping("/{id}/adresses")
	public Customer listAdresses(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}


}
