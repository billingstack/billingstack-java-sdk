package org.billingstack;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomersTest extends BillingStackTest {
	
	private User merchantUser;
	
	private Account merchantAccount;
	
	private Merchant merchant;
	
	private User customerUser;
	
	private Account customerAccount;
	
	private Customer customer;
	
	@Before
	public void before() {
		merchantUser = bs.users().create(new User() {{
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		merchantAccount = bs.accounts().create(new Account(){{
			setName("billingstack");
			setTitle("BillingStack");
		}});
		
		merchant = bs.merchants().create(new Merchant() {{
			setId(CustomersTest.this.merchantAccount.getId());
			setLanguage("en");
			setCurrency("usd");
		}});
		
		bs.account(merchantAccount.getId()).user(merchantUser.getId()).role(roles.get(1).getId()).create();
		
		customerUser = bs.users().create(new User() {{
			setUsername("luis11");
			setPassword("secret0");
		}});
		
		customerAccount = bs.accounts().create(new Account(){{
			setName("woorea");
			setTitle("Woorea");
		}});
		
		customer = bs.merchant(merchant.getId()).customers().create(new Customer() {{
			setId(CustomersTest.this.customerAccount.getId());
			setLanguage("es");
			setCurrency("eur");
		}});
		
		bs.account(customerAccount.getId()).user(customerUser.getId()).role(roles.get(2).getId()).create();
	}
	
	@After
	public void after() {
		
		List<Merchant> merchants = bs.merchants().list();
		for(Merchant m : merchants) {
			bs.account(m.getId()).delete();
			bs.merchant(m.getId()).delete();
		}
		List<User> users = bs.users().list();
		for(User u : users) {
			bs.user(u.getId()).delete();
		}
		
	}
	
	@Test
	public void list() {
		
		List<Customer> customers = bs.merchant(merchant.getId()).customers().list();
		
		Assert.assertTrue(customers.size() > 0);
	}
	
	@Test
	public void create() {
		
		Assert.assertNotNull(customerUser.getId());
		Assert.assertNotNull(customerAccount.getId());
		Assert.assertNotNull(customer.getId());
		Assert.assertEquals(customerAccount.getId(), customer.getId());
	
	}
	
	
	@Test
	public void show() {
		
		Customer result = bs.merchant(merchant.getId()).customer(customer.getId()).show();
		
		Assert.assertNotNull(result.getId());
		
	}
	
	/*
	@Test
	public void update() {
		List<Merchant> merchants = bs.merchants().list();
	}
	*/
	
	@Test
	public void delete() {
		
		bs.merchant(merchant.getId()).customer(customer.getId()).delete();
		
		List<Customer> customers = bs.merchant(merchant.getId()).customers().list();
		
		Assert.assertTrue(customers.size() == 0);
		
	}
	
}
