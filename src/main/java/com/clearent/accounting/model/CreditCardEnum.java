package com.clearent.accounting.model;

public enum CreditCardEnum {
	/**
	 * Enum class with the credit card types and interest rate (%) associated with
	 * it.
	 */

	VISA("VISA", 10), MASTERCARD("MASTERCARD", 5), DISCOVER("DISCOVER", 1);

	private final String creditCardType;
	private final double interestRate;

	private CreditCardEnum(String creditCardType, double interestRate) {
		this.creditCardType = creditCardType;
		this.interestRate = interestRate;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public double getInterestRate() {
		return interestRate;
	}
}
