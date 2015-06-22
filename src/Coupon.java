package com.jeannius.cs401.project;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class Coupon implements Comparable {


    private String couponProviderName, productName, methodNameToIterate;
    private Double price, discountRate;
    private Integer expirationDate;
    private CouponStatus status;
//    public static String REDEEMED ="Redeemed", UNUSED="Unused";

    public Coupon() {

    }


    public void setProviderName(String name) {
        this.couponProviderName = name;
    }


    public void setProductName(String name) {
        this.productName = name;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public void setDiscountRate(double discount) {
        this.discountRate = discount;
    }


    public void setExpirationDate(int expiration) {
        this.expirationDate = expiration;
    }


    public void setCouponStatus(CouponStatus status) {
        System.out.println("\n"+status+"\n");
        this.status = status;
    }

    public void setMethodNameToIterate(String methodName){
        this.methodNameToIterate = methodName;
    }


    public String getCouponProviderName(){
        return  couponProviderName;
    }

    public String getProductName(){
        return  productName;
    }

    public Double getPrice(){
        return price;
    }

    public Double getDiscountRate(){
        return discountRate;
    }

    public Integer getExpirationDate(){
        return expirationDate;
    }

    public String getMethodNameToIterate(){
        return methodNameToIterate;
    }

    public CouponStatus  getStatus(){
        return status;
    }

    public Double getFinalPrice(){
      return  getPrice() * (1 - (getDiscountRate() / 100));
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
