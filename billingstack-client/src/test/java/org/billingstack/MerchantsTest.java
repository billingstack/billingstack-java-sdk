package org.billingstack;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This test actually tests the merchants endpoint
 * 
 * @author sp
 *
 */
public class MerchantsTest extends BillingStackTest {
	
	protected User merchantUser;
	
	protected Account merchantAccount;
	
	protected Merchant merchant;
	
	/**
	 * this is done before each test method
	 */
	@Before
	public void before() {
		
		//create a user, account, merchant, and grant as merchant_admin
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
			setName(MerchantsTest.this.merchantAccount.getName());
			setTitle(MerchantsTest.this.merchantAccount.getTitle());
			setLanguage("en");
			setCurrency("usd");
		}});
		
		bs.account(merchantAccount.getId()).user(merchantUser.getId()).role(roles.get(1).getId()).create();
	}
	
	/**
	 * after each test i drop merchant stuff
	 */
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
		//drop master data
		super.after();
	}
	
	@Test
	public void list() {
		
		List<Merchant> merchants = bs.merchants().list();
		//since @before a merchant is created
		Assert.assertTrue(merchants.size() > 0);
	}
	
	@Test
	public void create() {
		//since @before a merchant is created
		Assert.assertNotNull(merchantUser.getId());
		Assert.assertNotNull(merchantAccount.getId());
		Assert.assertNotNull(merchant.getId());
		Assert.assertEquals(merchantAccount.getId(), merchant.getId());
	
	}
	
	
	@Test
	public void show() {
		//since @before a merchant is created
		Merchant result = bs.merchant(merchant.getId()).show();
		
		Assert.assertNotNull(result.getId());
		
	}
	
	//TODO : updates not implemented yet
	/*
	@Test
	public void update() {
		List<Merchant> merchants = bs.merchants().list();
	}
	*/
	
	@Test
	public void delete() {
		
		bs.account(merchant.getId()).delete();
		bs.merchant(merchant.getId()).delete();
		
		List<Merchant> merchants = bs.merchants().list();
		
		Assert.assertTrue(merchants.size() == 0);
		
	}
	
}
