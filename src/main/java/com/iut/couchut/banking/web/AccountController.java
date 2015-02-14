package com.iut.couchut.banking.web;

import com.iut.couchut.banking.domain.Account;
import com.iut.couchut.banking.repository.AccountRepository;
import com.iut.couchut.banking.web.dto.AccountDTO;
import com.iut.couchut.banking.web.exception.DataIntegrityException;
import com.iut.couchut.banking.web.exception.ErrorCode;
import com.iut.couchut.banking.web.exception.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.iut.couchut.banking.web.exception.ErrorCode.NO_ENTITY_FOUND;

/**
 * Created by Valentin on 08/02/2015.
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> list() {
        List<Account> accounts = from(accountRepository.findAll()).toList();
        List<AccountDTO> accountDTOs = newArrayList();
        mapper.map(accounts, accountDTOs);
        return accountDTOs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountDTO getById(@PathVariable long id) {
        Account account = accountRepository.findOne(id);

        if(null == account) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }

        return mapper.map(account, AccountDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody AccountDTO todoDTO) {
        Account account = mapper.map(todoDTO, Account.class);

        Account savedAccount;
        try {
            savedAccount = accountRepository.save(account);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(ErrorCode.WRONG_ENTITY_INFORMATION);
        }

        return mapper.map(savedAccount, AccountDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AccountDTO update(@PathVariable long id, @RequestBody AccountDTO accountDTO) {
        Account account = mapper.map(accountDTO, Account.class);

        Account accountToUpdate = accountRepository.findOne(id);

        if(null == accountToUpdate) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }

        account.setId(accountToUpdate.getId());
        Account updatedAccount = accountRepository.save(account);

        return mapper.map(updatedAccount, AccountDTO.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        try {
            accountRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }
    }



}
