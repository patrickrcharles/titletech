package com.titletech.entity;

import javax.persistence.*;

@Entity
@Table(name = "parcel_ownership")
public class ParcelOwnership {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="current_ownerid")
    private int currentOwnerid;

    @Column(name="previous_ownerid")
    private int previousOwnerid;

    @Column(name="current_owner")
    private String currentOwner;

    @Column(name="previous_owner")
    private String previousOwner;

    @Column(name="parcelid")
    private int parcelid;

    @Column(name="parcel_documentid")
    private int parcelDocumentid;

    @Column(name="date_purchased")
    private String datePurchased;

    @Column(name="date_sold")
    private String dateSold;


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

    public ParcelOwnership() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getParcelid() {
        return parcelid;
    }

    public void setParcelid(int parcelid) {
        this.parcelid = parcelid;
    }

    public int getParcelDocumentid() {
        return parcelDocumentid;
    }

    public void setParcelDocumentid(int parcelDocumentid) {
        this.parcelDocumentid = parcelDocumentid;
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
        return "ParcelOwnership{" +
                "id=" + id +
                ", current_ownerid=" + currentOwnerid +
                ", previous_ownerid=" + previousOwnerid +
                ", parcelid=" + parcelid +
                ", parcelDocumentid=" + parcelDocumentid +
                ", datePurchased='" + datePurchased + '\'' +
                ", dateSold='" + dateSold + '\'' +
                '}';
    }

}
