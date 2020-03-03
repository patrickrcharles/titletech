package com.titletech.entity;

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
    private int currentOwnerid;

    @Column(name="previous_ownerid")
    private int previousOwnerid;

    @Column(name="current_owner")
    private String currentOwner;

    @Column(name="previous_owner")
    private String previousOwner;

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

    public int getCurrentOwnerid() {
        return currentOwnerid;
    }

    public void setCurrentOwnerid(int currentOwnerid) {
        this.currentOwnerid = currentOwnerid;
    }

    public int getPreviousOwnerid() {
        return previousOwnerid;
    }

    public void setPreviousOwnerid(int previousOwnerid) {
        this.previousOwnerid = previousOwnerid;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    public String getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(String previousOwner) {
        this.previousOwner = previousOwner;
    }
}
