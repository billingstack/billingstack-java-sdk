package org.billingstack;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomersTest extends MerchantsTest {
	
	protected User customerUser;
	
	protected Account customerAccount;
	
	protected Customer customer;
	
	@Before
	public void before() {
		super.before();
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
			setName(CustomersTest.this.customerAccount.getName());
			setTitle(CustomersTest.this.customerAccount.getTitle());
			setLanguage("es");
			setCurrency("eur");
		}});
		
		bs.account(customerAccount.getId()).user(customerUser.getId()).role(roles.get(2).getId()).create();
	}
	
	@After
	public void after() {
		super.after();
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
