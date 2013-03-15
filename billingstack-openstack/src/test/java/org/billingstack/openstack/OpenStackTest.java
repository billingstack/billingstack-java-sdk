package org.billingstack.openstack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Currency;
import org.billingstack.FixedPlanItem;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.Product;
import org.billingstack.TimePlanItem;
import org.billingstack.TimeRangePricing;
import org.billingstack.VolumePlanItem;
import org.billingstack.VolumeRangePricing;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OpenStackTest {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	private static BillingStack client;
	
	protected static BillingStackEndpoint bs;
	
	private static final String TEST_SOURCE = "eu";
	
	private Language language;
	
	private Currency currency;
	
	private Merchant merchant;
	
	@BeforeClass
	public static void beforeClass() {
		client = new BillingStack();
	}
	
	@AfterClass
	public static void afterClass() {
		client.close();
	}
	
	@Before
	public void before() {
		bs = client.create(ENDPOINT);
		
		language = bs.languages().create(new Language() {{
			setName("en");
			setTitle("English");
		}});
		
		currency = bs.currencies().create(new Currency() {{
			setName("usd");
			setTitle("US Dollar");
		}});
		
		merchant = bs.merchants().create(new Merchant() {{
			setId("merchant.1");
			setLanguage("en");
			setCurrency("usd");
		}});
		
		OpenStackProvider.install(bs, merchant.getId(), TEST_SOURCE);
	}
	
	@After
	public void after() {
		bs.merchant(merchant.getId()).delete();
		bs.currency(currency.getName()).delete();
		bs.language(language.getName()).delete();
	}

	@Test
	public void createPlan() {
		
		MerchantTarget mt = bs.merchant(merchant.getId());
		
		final List<Product> products = mt.products().list();
		
		mt.plans().create(new Plan() {{
			setName("plan.m");
			setTitle("Plan M");
		}});
		
		final List<Plan> plans = mt.plans().list();
		
		
		String pid = product(products, OpenStackProvider.NAME, TEST_SOURCE, "instance:m1.tiny");
		mt.plan(plans.get(0).getId()).item(pid).create(new FixedPlanItem(){{
			setPrice(new BigDecimal("0.99"));
		}});
		
		pid = product(products, OpenStackProvider.NAME, TEST_SOURCE, "instance:m1.tiny");
		mt.plan(plans.get(0).getId()).item(pid).update(new FixedPlanItem(){{
			setPrice(new BigDecimal("1.99"));
		}});
		
		pid = product(products, OpenStackProvider.NAME, TEST_SOURCE, "instance:m1.small");
		mt.plan(plans.get(0).getId()).item(pid).create(new VolumePlanItem(){{
			setPricing(new ArrayList<VolumeRangePricing>() {{
				VolumeRangePricing pricing0 = new VolumeRangePricing();
				pricing0.setEnd(new BigDecimal(9.99));
				pricing0.setPrice(new BigDecimal(10.00));
				add(pricing0);
				VolumeRangePricing pricing1 = new VolumeRangePricing();
				pricing1.setStart(new BigDecimal(10.00));
				pricing1.setEnd(new BigDecimal(19.99));
				pricing1.setPrice(new BigDecimal(8.00));
				add(pricing1);
				VolumeRangePricing pricing2 = new VolumeRangePricing();
				pricing2.setStart(new BigDecimal(20.00));
				pricing2.setPrice(new BigDecimal(5.00));
				add(pricing2);
			}});
		}});
		
		pid = product(products, OpenStackProvider.NAME, TEST_SOURCE, "instance:m1.large");
		mt.plan(plans.get(0).getId()).item(pid).create(new TimePlanItem(){{
			setPricing(new ArrayList<TimeRangePricing>() {{
				TimeRangePricing pricing0 = new TimeRangePricing();
				pricing0.setStart("08:00");
				pricing0.setEnd("14:59");
				pricing0.setPrice(new BigDecimal(8.00));
				add(pricing0);
				TimeRangePricing pricing1 = new TimeRangePricing();
				pricing1.setStart("15:00");
				pricing1.setEnd("07:59");
				pricing1.setPrice(new BigDecimal(8.00));
				add(pricing1);
			}});
		}});
		
		mt.plan(plans.get(0).getId()).show();
	}

	private static String product(List<Product> products, String provider, String source, String name) {
		for(Product p : products) {
			if(provider.equals(p.getProvider()) && source.equals(p.getSource()) && name.equals(p.getName())) {
				return p.getId();
			}
		}
		throw new RuntimeException("product.not.found");
	}
	
}
