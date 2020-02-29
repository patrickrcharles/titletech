package com.springboot.titletech.entity;

import javax.persistence.*;

@Entity
@Table(name = "parcel")
public class Parcel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="current_ownerid")
    private int current_ownerid;

    @Column(name="previous_ownerid")
    private int previous_ownerid;

    public Parcel() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getCurrent_ownerid() {
        return current_ownerid;
    }

    public void setCurrent_ownerid(int current_ownerid) {
        this.current_ownerid = current_ownerid;
    }

    public int getPrevious_ownerid() {
        return previous_ownerid;
    }

    public void setPrevious_ownerid(int previous_ownerid) {
        this.previous_ownerid = previous_ownerid;
    }
}
