package com.springboot.titletech.entity;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="parcelid")
    private int parcelid;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String middleName;

    @Column(name="middle_name")
    private String lastName;

    @Column(name="date_purchased")
    private String datePurchased;

    @Column(name="date_sold")
    private String dateSold;

    @Column(name="is_current_owner")
    private int isCurrentOwner;

    public int getIsCurrentOwner() {
        return isCurrentOwner;
    }

    public void setIsCurrentOwner(int isCurrentOwner) {
        this.isCurrentOwner = isCurrentOwner;
    }

    // define constructors
    public Person() {

    }

    public Person(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParcelid() {
        return parcelid;
    }

    public void setParcelid(int parcelid) {
        this.parcelid = parcelid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public String getDateSold() {
        return dateSold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", parcelid=" + parcelid +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", datePurchased='" + datePurchased + '\'' +
                ", dateSold='" + dateSold + '\'' +
                '}';
    }
}











