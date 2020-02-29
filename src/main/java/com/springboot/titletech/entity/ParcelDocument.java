package com.springboot.titletech.entity;

import javax.persistence.*;

@Entity
@Table(name = "parcel_document")
public class ParcelDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="parcelid")
    private int parcelid;

    @Column(name="date_purchased")
    private String datePurchased;

    @Column(name="date_sold")
    private String dateSold;

    @Column(name="current_ownerid")
    private int current_ownerid;

    @Column(name="previous_ownerid")
    private int previous_ownerid;

    @Column(name="current_owner")
    private String current_owner;

    @Column(name="previous_owner")
    private String previous_owner;

    public ParcelDocument() {
    }

    public long getId() {
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

    public String getCurrent_owner() {
        return current_owner;
    }

    public void setCurrent_owner(String current_owner) {
        this.current_owner = current_owner;
    }

    public String getPrevious_owner() {
        return previous_owner;
    }

    public void setPrevious_owner(String previous_owner) {
        this.previous_owner = previous_owner;
    }
}
