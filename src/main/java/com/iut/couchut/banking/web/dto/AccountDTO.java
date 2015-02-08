package com.iut.couchut.banking.web.dto;

import com.iut.couchut.banking.domain.Account;

/**
 * Created by Valentin on 08/02/2015.
 */
public class AccountDTO {

    private Account account;

    // Getter and Setter

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    // Constructor
    public AccountDTO() {
    }

    public AccountDTO(Account account) {

        this.account = account;
    }
}

