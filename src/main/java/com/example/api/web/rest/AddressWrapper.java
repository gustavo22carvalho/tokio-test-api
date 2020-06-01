package com.example.api.web.rest;

import java.util.List;

import com.example.api.domain.Address;

public class AddressWrapper {

    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}