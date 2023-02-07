package com.clearent.accounting.model;

import java.util.List;

import lombok.Data;

@Data
public class Wallet {

	private List<CreditCard> creditCards;

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wallet(List<CreditCard> creditCards) {
		super();
		this.creditCards = creditCards;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

}
