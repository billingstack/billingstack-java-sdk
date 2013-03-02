package org.billingstack.examples;

import java.util.HashMap;
import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.Currency;
import org.billingstack.Customer;
import org.billingstack.CustomerPaymentMethod;
import org.billingstack.CustomerTarget;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGateway;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.PaymentMethod;
import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.billingstack.Product;
import org.billingstack.Role;
import org.billingstack.Subscription;
import org.billingstack.User;

public class BillingStackExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingStack bs = new BillingStack(ENDPOINT);
		
		bs.roles().create(new Role() {{
			setName("billingstack_admin");
		}});
		
		final List<Role> roles = bs.roles().list();
		
		bs.languages().create(new Language() {{
			setName("en");
			setTitle("English");
		}});
		bs.languages().create(new Language() {{
			setName("es");
			setTitle("Spanish");
		}});
		
		final List<Language> languages = bs.languages().list();
		
		bs.currencies().create(new Currency() {{
			setName("usd");
			setTitle("US Dollar");
		}});
		bs.currencies().create(new Currency() {{
			setName("eur");
			setTitle("Euro");
		}});
		
		final List<Currency> currencies = bs.currencies().list();
		
		bs.paymentGatewayProviders().create(new PaymentGatewayProvider() {{
			setName("braintree");
			setTitle("Braintree");
			setDescription("Braintree Payments");
			setDefault(Boolean.TRUE);
			setMetadata(new HashMap<String, String>() {{
				put("test", "value1");
			}});
		}});
		
		final List<PaymentGatewayProvider> pgps = bs.paymentGatewayProviders().list();
		
		final PaymentMethod pgm = bs.paymentGatewayProvider(pgps.get(0).getId()).paymentMethods().create(new PaymentMethod() {{
			setName("visa");
		}});
		
		bs.merchants().create(new Merchant() {{
			setName("billingstack");
			setTitle("BillingStack");
			setLanguage(languages.get(0).getId());
			setCurrency(currencies.get(0).getId());
		}});
		
		bs.merchants().create(new Merchant() {{
			setName("openstackbiller");
			setTitle("OpenStack Biller");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		m.show();

		m.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		
		final List<User> merchantUsers = m.users().list();
		m.user(merchantUsers.get(0).getId()).show();

		
		m.products().create(new Product() {{
			setName("instance:m1.tiny");
			setTitle("instance:m1.tiny");
		}});
		m.products().create(new Product() {{
			setName("instance:m1.small");
			setTitle("instance:m1.small");
		}});
		
		final List<Product> products = m.products().list();

		m.product(products.get(0).getId()).show();

		m.plans().create(new Plan() {{
			setName("plan.s");
			setTitle("Plan S");
		}});
		m.plans().create(new Plan() {{
			setName("plan.m");
			setTitle("Plan M");
		}});
		final List<Plan> plans = m.plans().list();
		
//		m.plan(plans.get(0).getId()).items().create(new PlanItem(){{
//			setProduct(products.get(0).getId());
//		}});
		
		m.plan(plans.get(0).getId()).show();
		
		m.paymentGateways().create(new PaymentGateway() {{
			setProvider("braintree");
			setTitle("My Braintree");
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<PaymentGateway> paymentGateways = m.paymentGateways().list();
		
		m.paymentGateway(paymentGateways.get(0).getId()).show();
		
		Customer customer = m.customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		CustomerTarget c = m.customer(customer.getId());
		
		List<Customer> customers = m.customers().list();
		c.show();
//		
		
		final CustomerPaymentMethod cpm = c.paymentMethods().create(new CustomerPaymentMethod() {{
			setMethod(pgm.getId());
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<CustomerPaymentMethod> customerPaymentMethods = c.paymentMethods().list();
		
		c.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		List<User> users = c.users().list();
		c.user(users.get(0).getId()).show();
		
		c.subscriptions().create(new Subscription() {{
			setPaymentMethod(cpm.getId());
			setPlan(plans.get(0).getId());
			setResource("tenant:1234");
			
		}});
		List<Subscription> subscriptions = c.subscriptions().list();
		c.subscription(subscriptions.get(0).getId()).show();
		
		c.subscription(subscriptions.get(0).getId()).delete();
		c.user(users.get(0).getId()).delete();
		c.paymentMethod(customerPaymentMethods.get(0).getId()).delete();
		c.delete();
		
		m.paymentGateway(paymentGateways.get(0).getId()).delete();
		
		for(Plan p : plans) {
			m.plan(p.getId()).delete();
		}
		
		for(Product p : products) {
			m.product(p.getId()).delete();
		}
		
		m.user(merchantUsers.get(0).getId()).delete();
		
		for(PaymentGatewayProvider _pgp : pgps) {
			bs.paymentGatewayProvider(_pgp.getId()).delete();
		}
		
		
		for(Currency currency : currencies) {
			bs.currency(currency.getId()).delete();
		}
		
		for(Language language : languages) {
			bs.language(language.getId()).delete();
		}
		
		for(Role role : roles) {
			bs.role(role.getId()).delete();
		}
		
//		bs.merchant("123").customer("456").invoices().list();
//		bs.merchant("123").customer("456").invoices().create(new Invoice());
//		bs.merchant("123").customer("456").invoice("789").show();
//		bs.merchant("123").customer("456").invoice("789").delete();
	}

}
