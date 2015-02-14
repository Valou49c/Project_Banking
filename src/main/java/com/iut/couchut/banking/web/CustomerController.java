package com.iut.couchut.banking.web;

import com.iut.couchut.banking.domain.Customer;
import com.iut.couchut.banking.repository.CustomerRepository;
import com.iut.couchut.banking.web.dto.CustomerDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Valentin on 13/02/2015.
 */
//@RestController
//@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDTO> list(){

        List<Customer> accounts = from(customerRepository.findAll()).toList();
        List<CustomerDTO> accountDTOs = newArrayList();
        mapper.map(accounts, accountDTOs);
        return accountDTOs;
    }










}
