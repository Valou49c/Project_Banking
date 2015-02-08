package com.iut.couchut.banking.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Created by Valentin on 08/02/2015.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    private long balance;



    public static Builder newAccount() {
        return new Builder();
    }

    private Account(Builder builder) {
        this.name = builder.name;
        this.balance = builder.balance;
    }


    public static class Builder {
        private String name;
        private long balance;


        // Constructor

        public Builder() {
        }

        public Builder withAll(String name) {
            this.name = name;
            return this;
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

}
