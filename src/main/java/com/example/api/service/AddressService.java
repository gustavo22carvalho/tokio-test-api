package com.example.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> findByCustomerId(Long idCustomer) {
        return repository.findByCustomerId(idCustomer);
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address save(Address address) {
        return repository.save(address);
    }

    public List<Address> saveAll(List<Address> addresses) {
        return addresses.stream().filter(address -> address.getId() == null).map(this::save)
                .collect(Collectors.toList());
    }

    public void delete(Address address) {
        repository.delete(address);
    }

    public Optional<Address> findById(Long id) {
		return repository.findById(id);
	}
}