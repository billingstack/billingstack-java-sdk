package org.billingstack;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlansTest extends MerchantsTest {
	
	private static final String PROVIDER_NAME = "openstack";
	
	private static final String[] SOURCES = new String[]{"usa-west","usa-east"};
	
	private static final String[][] PRODUCTS = {
		
		{"instance","Gauge","","inst ID","Duration of instance"},
		{"memory","Gauge","B","inst ID","Volume of RAM in MB"}
	
	};
	
	protected List<Product> products;
	
	protected Plan plan;
	
	@Before
	public void before() {
		
		super.before();
		
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
		
		
		
		super.after();
	}
	
	@Test
	public void list() {
		
		List<Plan> plans = bs.merchant(merchant.getId()).plans().list();
		
		Assert.assertTrue(plans.size() == 1);
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
