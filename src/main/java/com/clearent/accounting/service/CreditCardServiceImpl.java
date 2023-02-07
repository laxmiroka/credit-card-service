package com.clearent.accounting.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.clearent.accounting.CreditCardServiceApplication;
import com.clearent.accounting.model.Wallet;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceApplication.class);

	@Override
	public double calculateInterest(double balanceAmount, double interest) throws Exception {
		try {
			return balanceAmount * interest / 100;
		} catch (Exception e) {
			logger.info("Exception occured while calculating the interest.");
			throw new Exception("Exception occured while calculating the interest.");
		}
	}

	@Override
	public double calculateInterestForAWallet(List<Wallet> wallets) throws Exception {
		return wallets.stream().filter(wallet -> wallet != null && wallet.getCreditCards() != null)
				.flatMap(wallet -> wallet.getCreditCards().stream()).mapToDouble(creditCard -> {
					try {
						return calculateInterest(creditCard.getBalance(), creditCard.getInterest());
					} catch (Exception e) {
						logger.info("Exception occured while calculating the interest.");
					}
					return 0;
				}).sum();
	}

}
