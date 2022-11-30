package com.vcs.abstractService;

import java.util.List;

import com.vcs.entity.Customer;

public interface AbstractBanking {
	
	//public int checkBalance();
	public void deposit(Customer customerValidated);
	public void withdrawal(Customer customerValidated);
	public List<Customer> transfer(List<Customer> customers, Customer customerSender);

}
