package com.clearent.accounting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.clearent.accounting.model.CreditCard;
import com.clearent.accounting.model.CreditCardEnum;
import com.clearent.accounting.model.Customer;
import com.clearent.accounting.model.Wallet;
import com.clearent.accounting.service.CreditCardService;
import com.clearent.accounting.service.CreditCardServiceImpl;

@RunWith(SpringRunner.class)
public class CreditCardServiceTest {

	private final double MOCK_INTEREST_OF_VISA = 10.0;
	private final double MOCK_INTEREST_OF_MC = 5.0;
	private final double MOCK_INTEREST_OF_DISCOVER = 1.0;
	private final double MOCK_COMBINED_INTEREST_OF_ALL_CREDIT_CARDS = 16.0;

	@TestConfiguration
	static class CreditCardImplTestContextConfiguration {

		@Bean
		public CreditCardService creditCardService() {
			return new CreditCardServiceImpl();
		}
	}

	@Autowired
	private CreditCardService creditCardService;

	Wallet wallet1, wallet2;
	CreditCard creditCard1, creditCard2, creditCard3;

	@Before
	public void setUp() {
		wallet1 = new Wallet();
		wallet2 = new Wallet();

		creditCard1 = new CreditCard(CreditCardEnum.VISA, 100, CreditCardEnum.VISA.getInterestRate());
		creditCard2 = new CreditCard(CreditCardEnum.MASTERCARD, 100, CreditCardEnum.MASTERCARD.getInterestRate());
		creditCard3 = new CreditCard(CreditCardEnum.DISCOVER, 100, CreditCardEnum.DISCOVER.getInterestRate());

	}

	/***
	 * 1 person has 1 wallet and 3 cards (1 Visa, 1 MC, 1 Discover) – Each Card has
	 * a balance of $100 – calculate the total interest (simple interest) for this
	 * person and per card.
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldCalculateTheIntrestOfOneWalletWithThreeCards() throws Exception {
		wallet1.setCreditCards(Stream.of(creditCard1, creditCard2, creditCard3).collect(Collectors.toList()));
		List<Wallet> wallets = new ArrayList<>(List.of(wallet1));
		Assert.assertEquals(MOCK_COMBINED_INTEREST_OF_ALL_CREDIT_CARDS,
				creditCardService.calculateInterestForAWallet(wallets), 0.0);

	}

	@Test
	public void shouldCalculateTheIntrestPerCreditCard() {
		try {
			Assert.assertEquals(MOCK_INTEREST_OF_VISA,
					creditCardService.calculateInterest(100, creditCard1.getInterest()), 0.0);
			Assert.assertEquals(MOCK_INTEREST_OF_MC,
					creditCardService.calculateInterest(100, creditCard2.getInterest()), 0.0);
			Assert.assertEquals(MOCK_INTEREST_OF_DISCOVER,
					creditCardService.calculateInterest(100, creditCard3.getInterest()), 0.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/***
	 * 1 person has 2 wallets Wallet 1 has a Visa and Discover , wallet 2 a MC -
	 * each card has $100 balance - calculate the total interest(simple interest)
	 * for this person and interest per wallet
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldCalculateTotalIntresetForAPersonWithMultipleWallets() throws Exception {
		wallet1.setCreditCards(Stream.of(creditCard1, creditCard3).collect(Collectors.toList()));
		wallet2.setCreditCards(Stream.of(creditCard2).collect(Collectors.toList()));
		Customer customer = new Customer(new ArrayList<>(List.of(wallet1, wallet2)));
		Assert.assertEquals(MOCK_COMBINED_INTEREST_OF_ALL_CREDIT_CARDS,
				creditCardService.calculateInterestForAWallet(customer.getWallets()), 0.0);

	}

	@Test
	public void shouldCalculateTotalIntresetPerWalletForAPersonWithMultipleWallets() throws Exception {
		wallet1.setCreditCards(Stream.of(creditCard1, creditCard3).collect(Collectors.toList()));
		wallet2.setCreditCards(Stream.of(creditCard2).collect(Collectors.toList()));
		Customer firstWallet = new Customer(new ArrayList<>(List.of(wallet1)));
		Assert.assertEquals(11.0, creditCardService.calculateInterestForAWallet(firstWallet.getWallets()), 0.0);
		Customer secondWallet = new Customer(new ArrayList<>(List.of(wallet2)));
		Assert.assertEquals(5.0, creditCardService.calculateInterestForAWallet(secondWallet.getWallets()), 0.0);

	}

	/***
	 * 2 people have 1 wallet each, person 1 has 1 wallet , with 2 cards MC and visa
	 * person 2 has 1 wallet – 1 visa and 1 MC - each card has $100 balance -
	 * calculate the total interest(simple interest) for each person and interest
	 * per wallet
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldCalculateTotalIntresetForEachPersonWithMultipleWallets() throws Exception {
		wallet1.setCreditCards(Stream.of(creditCard1, creditCard2).collect(Collectors.toList()));
		Customer customer1 = new Customer(new ArrayList<>(List.of(wallet1)));
		Assert.assertEquals(15.0, creditCardService.calculateInterestForAWallet(customer1.getWallets()), 0.0);

		wallet2.setCreditCards(Stream.of(creditCard1, creditCard2).collect(Collectors.toList()));
		Customer customer2 = new Customer(new ArrayList<>(List.of(wallet2)));
		Assert.assertEquals(15.0, creditCardService.calculateInterestForAWallet(customer2.getWallets()), 0.0);

	}

	@Test
	public void shouldCalculateTotalIntresetPerWallet() throws Exception {
		wallet1.setCreditCards(Stream.of(creditCard1, creditCard2).collect(Collectors.toList()));
		List<Wallet> firstWallet = new ArrayList<>(List.of(wallet1));
		Assert.assertEquals(15.0, creditCardService.calculateInterestForAWallet(firstWallet), 0.0);

		wallet2.setCreditCards(Stream.of(creditCard1, creditCard2).collect(Collectors.toList()));
		List<Wallet> secondWallet = new ArrayList<>(List.of(wallet1));
		Assert.assertEquals(15.0, creditCardService.calculateInterestForAWallet(secondWallet), 0.0);
	}

}
