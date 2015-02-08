package com.iut.couchut.banking.repository;

import com.iut.couchut.banking.domain.Account;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Valentin on 08/02/2015.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByName(String name, long balance);


}
