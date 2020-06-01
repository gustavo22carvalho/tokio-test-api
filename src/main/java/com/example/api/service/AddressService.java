package com.example.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.domain.Address;
import com.example.api.domain.exception.BusinessException;
import com.example.api.repository.AddressRepository;

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

    public void delete(Address address) throws BusinessException {
        if(this.findById(address.getId()) != null){
            repository.delete(address);
        }
    }

    public Address findById(Long id) throws BusinessException {
        if(id == null){
			throw new BusinessException("Identificador de endereço não pode ser vazio");
		}
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Endereço não foi encontrado"));
	}
}