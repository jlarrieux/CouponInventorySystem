package com.jeannius.cs401.project;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class Coupon{

    private String couponProviderName, productName;
    private double price, discountRate;
    private int expirationDate;
    private CouponStatus status;


    public Coupon(){

    }

    public void setProviderName(String name){
        this.couponProviderName = name;
    }

    public void setProductName(String name){
        this.productName = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setDiscountRate(double discount){
        this.discountRate = discount;
    }

    public void setExpirationDate(int expiration){
        this.expirationDate = expiration;
    }

    public void setCouponStatus(CouponStatus status){
        this.status = status;
    }



}
