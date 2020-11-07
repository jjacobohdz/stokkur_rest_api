package com.stokkur.am.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity representation of an Account.
 * 
 * @author Jacobo
 */
@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private Integer age;
    
    private double balance;
    
    // Required by jpa. Not meant to be accessed directly so this 'ctor is protected.
    protected Account() {}

    public Account(String firstName, String lastName, String email, Integer age, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", balance=" + balance + '}';
    }
    
}
