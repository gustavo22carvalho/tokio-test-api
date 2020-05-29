package com.example.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity   
public class Address {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
    private String cep;     //01001-000",
    
    @Column(nullable = false)
	@NotEmpty
    private String logradouro;      //Praça da Sé",
    
    @Column(nullable = false)
	@NotEmpty
    private String complemento;     //lado ímpar",
    
    @Column(nullable = false)
	@NotEmpty
    private String bairro;      //Sé",
    
    @Column(nullable = false)
	@NotEmpty
    private String localidade;      //São Paulo",
    
    @Column(nullable = false)
	@NotEmpty
    private String uf;      //SP",
    
    @Column(nullable = false)
	@NotEmpty
    private String unidade;     //"",
    
    @Column(nullable = false)
	@NotEmpty
    private String ibge;        //3550308",
    
    @Column(nullable = false)
	@NotEmpty
    private String gia;     //1004"

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}