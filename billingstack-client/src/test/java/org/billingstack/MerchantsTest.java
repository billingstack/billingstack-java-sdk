package org.billingstack;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MerchantsTest extends BillingStackTest {
	
	private User user;
	
	private Account account;
	
	private Merchant merchant;
	
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
			setId(MerchantsTest.this.account.getId());
			setLanguage("en");
			setCurrency("usd");
		}});
		
		List<Role> roles = bs.roles().list();
		
		bs.account(account.getId()).user(user.getId()).role(roles.get(0).getId()).create();
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
		
		List<Merchant> merchants = bs.merchants().list();
		
		Assert.assertTrue(merchants.size() > 0);
	}
	
	@Test
	public void create() {
		
		Assert.assertNotNull(user.getId());
		Assert.assertNotNull(account.getId());
		Assert.assertNotNull(merchant.getId());
		Assert.assertEquals(account.getId(), merchant.getId());
	
	}
	
	
	@Test
	public void show() {
		
		Merchant result = bs.merchant(merchant.getId()).show();
		
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
		
		bs.merchant(merchant.getId()).delete();
		
		List<Merchant> merchants = bs.merchants().list();
		
		Assert.assertTrue(merchants.size() == 0);
		
	}
	
}
