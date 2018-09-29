package com.cs602.hw1;

/**
 * Created by nan on 9/27/18.
 */
import java.util.Scanner;

public class BodyMassIndexCalculator {
    public static void main (String [] args) {

        Scanner input = new Scanner (System.in);

        int weight;
        int height;
        int bMI;

        System.out.print ("Enter Your Weight in Pounds: ");
        weight = input.nextInt();
        System.out.print ("Enter Your Height in Inches: ");
        height = input.nextInt();
        bMI = (weight * 703) / (height * height);
        System.out.printf ("Your Body Mass Index (BMI) is %d\n\n", bMI);

        System.out.println ("BMI VALUES");
        System.out.println ("Underweight: less than 18.5");
        System.out.println ("Normal:      between 18.5 and 24.9");
        System.out.println ("Overweight:  between 25 and 29.9");
        System.out.println ("Obese:       30 or greater");

    }
}
