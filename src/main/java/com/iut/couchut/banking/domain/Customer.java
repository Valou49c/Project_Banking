package com.iut.couchut.banking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Valentin on 08/02/2015.
 */
@Entity
public class Customer implements Serializable{

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;
    private String lastname;
    private Account account;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    // Constructor


    public Customer(long id, String name, String lastname) {

        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public Customer(long id, String name, String lastname, Account account) {

        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.account = account;
    }

    public Customer() {

    }


    private Customer(Builder builder){

        this.name = builder.name;
        this.lastname = builder.lastname;
    }

    // Builder

    public static class Builder{

        private String name;
        private String lastname;
        private Account account;

        public Builder withAll(String name, String lastname, Account account) {
            this.name = name;
            this.lastname = lastname;
            this.account = account;

            return this;
        }

        public Builder withoutAccount(String name, String lastname) {
            this.name = name;
            this.lastname = lastname;

            return this;
        }

        public Builder() {
        }

        public Customer build() {
            return new Customer(this);
        }
    }




}
