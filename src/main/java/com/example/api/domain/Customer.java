package com.example.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@SequenceGenerator(initialValue = 3, allocationSize = 1, name = "SEQ_CUSTOMER")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CUSTOMER")
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String name;

	@Column(nullable = false)
	@NotEmpty
	@Email
	private String email;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
	private List<Address> addresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
