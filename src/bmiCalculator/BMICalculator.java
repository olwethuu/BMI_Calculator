package bmiCalculator;

import java.util.Scanner;
import java.util.Locale;

public class BMICalculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.US);
		String user;
		
		char repeat = 0;
		
		do {
			
			// for the name of the user.
			System.out.println("Welcome to the BMI calculator. Please enter your name:");
			user = scanner.nextLine();
			
			
			int unitChoice = getUnitChoice(scanner, user);
			
			double weight = (unitChoice == 1) ? getValidInput(scanner, user +", enter your weight in kilograms: ", 10, 600) 
					: getValidInput(scanner, user+ ", enter your weight in pounds: ", 22, 1300);
			
			double height = (unitChoice == 1) ? getValidInput(scanner, user + ", enter your height in meters: ", 0.5, 2.5) 
					: getValidInput(scanner, user+", enter your height in inches: ", 20, 100);
			
			double bmi = calculateBMI(unitChoice, weight, height);
			System.out.println(user +", your BMI is: " +bmi);
			
			double category = displayCategory(bmi);
			
			repeat = askToRepeat(scanner, user);
			System.out.println();
		}
		
		while(repeat == 'Y' || repeat == 'y');

	}
	
	// Units (metric & imperial)
	public static int getUnitChoice(Scanner scanner, String user) {
		
		int choice;
	
		
		while(true) {
			System.out.println(user+ ", select a perferred unit:\n" 
					+ "1. Metric (kg, m)\n"
					+ "2. Imperial (lbs, in)\n"
					+ "Please select either option 1 or 2");
			
			if(scanner.hasNextInt()){
				choice = scanner.nextInt();
				scanner.nextLine();
				if(choice == 1 || choice == 2) {
					break;
			 	} else {
					System.out.println("Invalid choice. Please enter either 1 or 2");
				}
				
			} else {
				System.out.println("Invalid input. Please enter a number (1 or 2)");
				scanner.next();
			}
		}
		
		return choice;

	}
	
	public static double getValidInput(Scanner scanner, String prompt, double min, double max) {
		double value;
		
		while(true) {
			System.out.println(prompt);
			
			if(scanner.hasNextDouble()) {
				value = scanner.nextDouble();
				scanner.nextLine();
				
				if(value >= min && value <= max) {
					break;
				} else {
					System.out.printf("Please enter a value between %.1f and %.1f.\n", min, max);
				}
			} else {
				System.out.println("Invalid input. Please enter a value");
				scanner.next();
			}
		}
		
		return value;
	}
	
	public static double calculateBMI(int unitChoice, double weight, double height) {
		
		double totalBMI;
		
		if(unitChoice == 1) {
			totalBMI = weight / (height * height);
		} else if(unitChoice == 2){
			totalBMI = (703 * weight) / (height * height);
		}
		else {
			System.out.println("Invalid choice, try again later.");
			return -1.0;
		}
		
		return totalBMI;
	}
	
	public static double displayCategory(double bmi) {
		
		// categories for bmi 
		if(bmi < 16) {
			System.out.println("You are severely underweight");
		}
		else if(bmi >= 16.0 && bmi <= 18.5) {
			System.out.println("You are underweight");
		}
		else if(bmi >= 18.5 && bmi <= 24.9 ) {
			System.out.println("You are normal");
		}
		else if(bmi >= 25 && bmi <= 29.9 ) {
			System.out.println("You are overweight");
		}
		else if(bmi >= 30 && bmi <= 34.99) {
			System.out.println("You are moderately obese");
		}
		else if(bmi >= 35 && bmi <= 39.99) {
			System.out.println("You are severely obese");
		}
		else if(bmi >= 40) {
			System.out.println("You are morbidly obese");
		}
		else {
			System.out.println("Error, please try again later");
					}
		
		return bmi;
	}
	
	public static char askToRepeat(Scanner scanner, String user) {
		
		char repeat;
		
		System.out.println("Do you want to run the program again? (Y/y to continue or N/n to stop):");
		repeat = scanner.next().charAt(0);
		scanner.nextLine();
		
		if(repeat == 'y' || repeat == 'Y') {
			return repeat;
		}
		else if(repeat == 'n' || repeat == 'N') {
			System.out.println("Thank you for participating, " +user +". Till next time.");
			System.exit(0);
			return repeat;
			
		}
		else {
			System.out.println("Invalid answer. Try again later.");
		}
		
		return repeat;
		
	}
	
	}


