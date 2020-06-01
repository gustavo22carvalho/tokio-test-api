package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.api.domain.Customer;
import com.example.api.domain.exception.BusinessException;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Customer findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
	}

	public Customer save(Customer customer) throws BusinessException {
		validate(customer);
		return repository.save(customer);
	}

	public void delete(Customer customer) {
		this.repository.delete(customer);
	}

	public Page<Customer> findAll(Integer page, Integer size) {
		return repository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
	}

	public boolean validate(Customer customer) throws BusinessException {
		List<String> errors = new ArrayList<>();
		if(StringUtils.isEmpty(customer.getName())){
			errors.add("Nome não pode estar vazio");
		}
		if(StringUtils.isEmpty(customer.getEmail())){
			errors.add("E-mail não pode estar vazio");
		}
		if(errors.isEmpty()){
			return true;
		} else {
			throw new BusinessException("Formulários incompleto", errors);
		}
	}

	public Customer update(Customer customer) throws BusinessException {
		if(customer.getId() == null){
			throw new BusinessException("Cliente não está registrado");
		}
		this.validate(customer);
		Customer customerToUpdate = this.findById(customer.getId());
		customerToUpdate.setName(customer.getName());
		customerToUpdate.setEmail(customer.getEmail());
		return this.repository.save(customerToUpdate);
	}
}
