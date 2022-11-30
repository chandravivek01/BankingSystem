package com.vcs.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.vcs.abstractService.AbstractBanking;
import com.vcs.entity.Customer;
import com.vcs.service.Banking;

public class Driver {
	
	public static void main(String[] args) {
		
		String accountNo;
		String password;
		int option, option2=1;
		
		boolean customerFound = false;
		Customer customerValidated = null;
		List<Customer> customers = new ArrayList<Customer>();
			
		Scanner scanner = new Scanner(System.in);
		
		Customer customer1 = new Customer("1800300411","pass1", "name1", 1000);
		Customer customer2 = new Customer("1800700811","pass2", "name2", 1800);
		customers.add(customer1);
		customers.add(customer2);
		
		AbstractBanking abstractBanking = new Banking();
		do {
			
			System.out.println("\n********* Welcome to the Main Login Page *********** ");
			System.out.println(" \nEnter your Credentials ..., press ENTER ");
			scanner.nextLine();
			
			System.out.println("Enter Bank Account Number: ");
			accountNo = scanner.nextLine();
			
			System.out.println("Enter password: ");
			password = scanner.nextLine();
			
			//Validate Bank Login Credentials
			for(Customer c: customers) {
				if(accountNo.equals(c.getAccountNo()) && password.equals(c.getPassword())) {
					customerFound = true;
					customerValidated = c;
					break;
				}
			}
			if(customerFound) {
			//if(accountNo.equals(customer1.getAccountNo()) && password.equals(customer1.getPassword())) {
				System.out.println("\n******** Welcome, "+ customerValidated.getName().toUpperCase() + " ,to our Banking System ******** \n");
				do {
					System.out.println("Choose from Our below Services: ");
					System.out.println("1. Check Balance ");
					System.out.println("2. Deposit ");
					System.out.println("3. Withdrawal ");
					System.out.println("4. Transfer ");
					System.out.println("0. LogOut ");
					System.out.println("5. Exit ");
					
					option = scanner.nextInt();
					switch(option) {
						case 0:
							option=0;
							break;
						case 1:
							System.out.println("Your Balance is: " + customerValidated.getBalance() + "\n");
							break;
						case 2:
							abstractBanking.deposit(customerValidated);
							break;
						case 3:
							abstractBanking.withdrawal(customerValidated);
							break;
						case 4:
							List<Customer> customerss=abstractBanking.transfer(customers, customerValidated);
							customers = customerss;
							break;
						case 5:
							option2=5;
							option=0;
							break;
						default:
							System.out.println("Enter a valid option from the above !!");
							break;
					}
				}
				while(option!=0);
				System.out.println(customerValidated.getName().toUpperCase() + ", You are Successfully Logged-Out ... \n");
			}
			else {
				System.out.println("Wrong Credentials, try again ... ");
			}

			
		}while(option2!=5);
		System.out.println("Successfully Exited ..., Program Terminated !! ");
					
	}

}
