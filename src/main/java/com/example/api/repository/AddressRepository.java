package com.example.api.repository;

import java.util.List;

import com.example.api.domain.Address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{
    
    List<Address> findByCustomerId(Long idCustomer);
    
    List<Address> findAll();
}