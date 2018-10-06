package BMI_Calculator;
import java.util.Scanner;


public class BMIClaculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inout your weight (Kg):");    // Enter your weight 
		double weight = scanner.nextDouble();             // Use a double type to store the input  
		System.out.println("Inout your height (m):");     // Enter your height
		double height = scanner.nextDouble();             // Use a double type to store the input
		CalculateBMI(weight, height);                     // call by value the method "CalculateBMI" 
	}

	private static void CalculateBMI(double weight, double height) {
		// TODO Auto-generated method stub
		double bmi = weight / Math.pow(height, 2) ;       // use the weight and height to Calculate the bmi   
		if(bmi<18.5) 
			System.out.println("You are underweight.");   // use the "if" and "else if" to set the condition 
		else if (bmi <24.9)
			System.out.println("You are normal.");
		else if (bmi < 29.9)
			System.out.println("You are overweight.");
		else 
			System.out.println("You are Obese.");		
	}

}
