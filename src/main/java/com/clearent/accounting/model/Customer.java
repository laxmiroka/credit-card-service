package com.clearent.accounting.model;

import java.util.List;

import lombok.Data;

@Data
public class Customer {

	private List<Wallet> wallets;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(List<Wallet> wallets) {
		super();
		this.wallets = wallets;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

}
