package com.iut.couchut.banking.web.dto;

import com.iut.couchut.banking.domain.Customer;

/**
 * Created by Valentin on 08/02/2015.
 */
public class CustomerDTO {

    private Customer customer;

    // Getter and Setter

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Constructor
    public CustomerDTO(Customer customer) {

        this.customer = customer;
    }
    public CustomerDTO() {
    }
}
