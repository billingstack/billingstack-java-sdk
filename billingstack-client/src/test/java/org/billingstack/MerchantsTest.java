package org.billingstack;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MerchantsTest extends BillingStackTest {
	
	protected User merchantUser;
	
	protected Account merchantAccount;
	
	protected Merchant merchant;
	
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
			setId(MerchantsTest.this.merchantAccount.getId());
			setLanguage("en");
			setCurrency("usd");
		}});
		
		bs.account(merchantAccount.getId()).user(merchantUser.getId()).role(roles.get(0).getId()).create();
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
		
		Assert.assertNotNull(merchantUser.getId());
		Assert.assertNotNull(merchantAccount.getId());
		Assert.assertNotNull(merchant.getId());
		Assert.assertEquals(merchantAccount.getId(), merchant.getId());
	
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
