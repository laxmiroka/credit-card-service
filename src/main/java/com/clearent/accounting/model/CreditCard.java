package com.clearent.accounting.model;

import lombok.Data;

@Data
public class CreditCard {
	private CreditCardEnum creditCardType;
	private double balance;
	private double interest;

	public CreditCard(CreditCardEnum creditCardType, double balance, double interest) {
		super();
		this.creditCardType = creditCardType;
		this.balance = balance;
		this.interest = interest;
	}

	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	public CreditCardEnum getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardEnum creditCardType) {
		this.creditCardType = creditCardType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

}
