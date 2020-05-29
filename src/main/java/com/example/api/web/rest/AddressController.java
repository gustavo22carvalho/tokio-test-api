package com.example.api.web.rest;

import java.util.List;

import com.example.api.domain.Address;
import com.example.api.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/adresses")
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

	@PostMapping
	public ResponseEntity<List<Address>> create(List<Address> adresses){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(adresses));
	}

	@DeleteMapping
	public Address delete(Address address){
		service.delete(address);
		return address;
	}
    
    @GetMapping("/{id}")
	public Address findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
	}
	
	@GetMapping("/{id}/adresses")
	public List<Address> listAdresses(@PathVariable Long id) {
		return service.findByCustomerId(id);
	}


}
