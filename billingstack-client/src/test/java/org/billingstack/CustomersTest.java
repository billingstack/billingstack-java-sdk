package org.billingstack;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomersTest extends MerchantsTest {
	
	protected Customer customer;
	
	@Before
	public void before() {
		super.before();
		
		customer = bs.merchant(merchant.getId()).customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		
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
		Assert.assertNotNull(customer.getId());
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
