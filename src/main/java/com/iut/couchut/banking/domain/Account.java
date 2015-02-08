package com.iut.couchut.banking.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Valentin on 08/02/2015.
 */
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private long id;



    @Column(nullable = false)
    private String name;

    private String balance;
    private AccountType type;
    private AccountOperations operations;
    private Customer customer;


    public Account(long id, String name, String balance, AccountType type, AccountOperations operations, Customer customer) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.operations = operations;
        this.customer = customer;
    }



    public static Builder newAccount() {
        return new Builder();
    }

    private Account(Builder builder) {
        this.name = builder.name;
        this.balance = builder.balance;
    }


    public static class Builder {
        private String name;
        private String balance;
        private AccountType type;
        private AccountOperations operations;
        private Customer customer;

        public Builder withAll(String name, String balance) {
            this.name = name;
            this.balance = balance;
//            this.type = type;
//            this.operations = operations;
//            this.customer = customer;

            return this;
        }
        // Constructor

        public Builder() {
        }


        public Account build() {
            return new Account(this);
        }
    }

    // Constructor

    public Account() {
    }



    // Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
