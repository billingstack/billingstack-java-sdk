package org.billingstack;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlansTest extends BillingStackTest {
	
	private static final String PROVIDER_NAME = "openstack";
	
	private static final String[] SOURCES = new String[]{"usa-west","usa-east"};
	
	private static final String[][] PRODUCTS = {
		
		{"instance","Gauge","","inst ID","Duration of instance"},
		{"memory","Gauge","B","inst ID","Volume of RAM in MB"}
	
	};
	
	private User user;
	
	private Account account;
	
	private Merchant merchant;
	
	private List<Product> products;
	
	private Plan plan;
	
	@Before
	public void before() {
		user = bs.users().create(new User() {{
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		account = bs.accounts().create(new Account(){{
			setName("billingstack");
			setTitle("BillingStack");
		}});
		
		merchant = bs.merchants().create(new Merchant() {{
			setId(PlansTest.this.account.getId());
			setLanguage("en");
			setCurrency("usd");
		}});
		
		bs.account(account.getId()).user(user.getId()).role(roles.get(0).getId()).create();
		
		products = new ArrayList<Product>();
		
		MerchantTarget mt = bs.merchant(merchant.getId());
		
		for(final String sName : SOURCES) {
			
			for(final String[] properties : PRODUCTS) {
				products.add(mt.products().create(new Product(){{
					setProvider(PROVIDER_NAME);
					setSource(sName);
					setName(properties[0]);
					setTitle(properties[0]);
				}}));
			}
		}
		
		plan = mt.plans().create(new Plan() {{
			setName("plan.m");
			setTitle("Plan M");
		}});
		
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
		
		List<Plan> plans = bs.merchant(merchant.getId()).plans().list();
		
		Assert.assertTrue(plans.size() > 0);
	}
	
	@Test
	public void create() {
		
		Assert.assertNotNull(plan.getId());
	
	}
	
	
	@Test
	public void show() {
		
		Plan result = bs.merchant(merchant.getId()).plan(plan.getId()).show();
		
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
		
		bs.merchant(merchant.getId()).plan(plan.getId()).delete();
		
		List<Plan> plans = bs.merchant(merchant.getId()).plans().list();
		
		Assert.assertTrue(plans.size() == 0);
		
	}
	
}
