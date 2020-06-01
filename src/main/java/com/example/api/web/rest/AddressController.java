package com.example.api.web.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.example.api.domain.Address;
import com.example.api.domain.exception.BusinessException;
import com.example.api.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private AddressService service;

	@Autowired
	public AddressController(AddressService service) {
		this.service = service;
	}

	@GetMapping
	public List<Address> findAll() {
		return service.findAll();
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<List<Address>> create(@RequestBody AddressWrapper addresses) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(addresses.getAddresses()));
	}

	@DeleteMapping
	public Address delete(@RequestBody Address address) throws BusinessException {
		service.delete(address);
		return address;
	}
    
    @GetMapping("/{id}")
	public Address findById(@PathVariable Long id) throws BusinessException {
		return service.findById(id);
	}
	
	@GetMapping("/{id}/addresses")
	public List<Address> listAddresses(@PathVariable Long id) {
		return service.findByCustomerId(id);
	}


}
