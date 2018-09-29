package com.cs602.hw1;

public class FBUserGrowth {

    public static int getMonth(double principal, double rate, double amount){
        return (int)(Math.log(amount/principal)/Math.log(1.0 + rate));
    }

    public static void main (String [] args) {

        System.out.printf("\nUser base will be 1,500,000,000 in these months:\n");
        System.out.println(getMonth(1000000000.0, 0.04, 1500000000.0));

        System.out.printf("\nUser base will be 2,000,000,000 in these months:\n");
        System.out.println(getMonth(1000000000.0, 0.04, 2000000000.0));

    }
}


