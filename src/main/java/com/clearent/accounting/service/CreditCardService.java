package com.clearent.accounting.service;

import java.util.List;

import com.clearent.accounting.model.CreditCardEnum;
import com.clearent.accounting.model.Wallet;

public interface CreditCardService {
	double calculateInterest(double balanceAmount, double interest) throws Exception;

	double calculateInterestForAWallet(List<Wallet> wallet) throws Exception;

}
