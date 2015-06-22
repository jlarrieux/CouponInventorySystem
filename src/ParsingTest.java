package com.jeannius.cs401.project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jeannius on 6/21/2015.
 */
public class ParsingTest {


    public static void main(String[]args) throws IOException {

        FileInputStream inFile;
        String fileName ="coupon.txt";
        StringBuffer progromText = new StringBuffer();
        String current ="";

        inFile = new FileInputStream(fileName);

        int c;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
        boolean succeed = false;
        current = reader.readLine();
//       while(current!=null){
           parser(current);
           current= reader.readLine();
//           System.out.println(current);
//       }




    }


    private static Coupon createCoupon(String c){
        String[] coup= parser(c);

        Coupon coupon = new Coupon();
        coupon.setProviderName(coup[0]);
        coupon.setProductName(coup[1]);
        coupon.setPrice(Double.valueOf(coup[2]));
        coupon.setDiscountRate(Double.valueOf(coup[3]));
        coupon.setExpirationDate(Integer.valueOf(coup[4]));
        coupon.setCouponStatus(CouponStatus.valueOf(coup[5]));

        return coupon;
    }
//

    private static String[] parser(String c){
        String[] red =new String[6];
        String current ="";
        int j=0, i=0;

        while(j<c.length()) {
            current="";

            while ( j<c.length()) {
                if(String.valueOf(c.charAt(j)).equals(",")) break;
                current = current + c.charAt(j);
                j++;
            }

            red[i]= current;
            i++;
            j++;

        }



        return red;
    }











}
