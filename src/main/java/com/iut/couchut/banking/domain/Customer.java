package com.iut.couchut.banking.domain;

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




}
