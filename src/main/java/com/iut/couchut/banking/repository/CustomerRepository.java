package com.iut.couchut.banking.repository;

import com.iut.couchut.banking.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Valentin on 13/02/2015.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

//    Customer FindByName(String name);
//

}
