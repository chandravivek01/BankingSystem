package com.vcs.service;

import java.util.List;
import java.util.Scanner;

import com.vcs.abstractService.AbstractBanking;
import com.vcs.entity.Customer;

public class Banking implements AbstractBanking {
	
	//int balance;
	int amount;
	int otp;
	int otpGenerated;
	String accountNo;
	boolean customerFound=false;
	Customer customerReceiver;
	
	Scanner scanner = new Scanner(System.in);

//	@Override
//	public int checkBalance() {
//		return this.balance;
//	}

	@Override
	public void deposit(Customer customerValidated) {
		int balanced = customerValidated.getBalance();
		
		System.out.println("Enter Amount to be Deposited: ");
		amount = scanner.nextInt();
		
		//validate amount
		if(amount>0) {
			System.out.println("Amount Deposited Successfully ... ");
			balanced += amount;
			customerValidated.setBalance(balanced);
			System.out.println("Your Updated Balance is: " + balanced + "\n");
		}
		else
			System.out.println("Invalid Amount, Deposit failed ... \n");
	}

	@Override
	public void withdrawal(Customer customerValidated) {
		int balanced = customerValidated.getBalance();
		
		System.out.println("Enter Amount to be withdrawn: ");
		amount = scanner.nextInt();
		
		//validate withdrawal
		if(balanced-amount>=0) {
			System.out.println("\nAmount Withdrawal Successfully ... ");
			balanced -= amount;
			customerValidated.setBalance(balanced);
			System.out.println("Your Updated Balance is: " + balanced + "\n");
		}
		else
			System.out.println("Insufficient funds, Withdrawal failed ...  \n");
	}

	@Override
	public List<Customer> transfer(List<Customer> customers, Customer customerSender) {
		int senderBalance=customerSender.getBalance();
		
		OtpGenerator otpGenerator = new OtpGenerator();
		otpGenerated = otpGenerator.otpGenerator();
		System.out.println("Verification OTP: " + otpGenerated);
		
		System.out.println("Enter the Generated OTP: ");
		otp = scanner.nextInt();
		
		//validate OTP
		if(otp==otpGenerated) {
			System.out.println("OTP Verification Successful ... \n");
			System.out.println("Enter the amount to be transferred: ");
			amount = scanner.nextInt();
			
			scanner.nextLine();
			System.out.println("Enter the Account Number to which transfer has to be done: ");
			accountNo = scanner.nextLine();
			
			for(Customer c: customers) {
				if(accountNo.equals(c.getAccountNo())) {
					customerFound = true;
					customerReceiver = c;
					break;
				}
			}
			//validate Receiver Bank Account
			if(customerFound) {
				//validate transfer amount
				if(senderBalance-amount>=0) {
					int  balanced= customerReceiver.getBalance();
					balanced += amount;
					customerReceiver.setBalance(balanced);
					
					System.out.println("Amount Transferred Successfully ... ");
					senderBalance -= amount;
					customerSender.setBalance(senderBalance);
					System.out.println("Your Updated Balance: " + senderBalance + "\n");
					return customers;
				}
				else {
					System.out.println("Insufficient funds, Transfer failed ... \n");
					return customers;
				}
					
			}
			else {
				System.out.println("Enter a Valid Bank Account Number !! \n ");
				return customers;
			}
				
		}
		else {
			System.out.println("Invalid OTP, Transfer failed ... \n");
			return customers;
		}		
			
	}

}
