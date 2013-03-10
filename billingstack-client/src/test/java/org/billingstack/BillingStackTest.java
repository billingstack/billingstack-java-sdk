package org.billingstack;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;

public abstract class BillingStackTest {

	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	protected BillingStack bs;
	
	protected List<Role> roles;
	
	protected List<Language> languages;
	
	protected List<Currency> currencies;
	
	protected List<InvoiceState> invoiceStates;
	
	protected List<PaymentGatewayProvider> paymentGatewayProviders;
	
	protected PaymentMethod paymentMethod;
	
	@Before
	public void before() {
		bs = new BillingStack(ENDPOINT);
		
		bs.roles().create(new Role() {{
			setName("billingstack_admin");
		}});
		
		bs.roles().create(new Role() {{
			setName("merchant_admin");
		}});
		
		
		bs.roles().create(new Role() {{
			setName("customer_admin");
		}});
		
		roles = bs.roles().list();
		
		bs.languages().create(new Language() {{
			setName("en");
			setTitle("English");
		}});
		bs.languages().create(new Language() {{
			setName("es");
			setTitle("Spanish");
		}});
		
		languages = bs.languages().list();
		
		bs.currencies().create(new Currency() {{
			setName("usd");
			setTitle("US Dollar");
		}});
		bs.currencies().create(new Currency() {{
			setName("eur");
			setTitle("Euro");
		}});
		
		currencies = bs.currencies().list();
		
		bs.invoiceStates().create(new InvoiceState() {{
			setName("pending");
		}});
		
		bs.invoiceStates().create(new InvoiceState() {{
			setName("completed");
		}});
		
		bs.invoiceStates().create(new InvoiceState() {{
			setName("failed");
		}});
		
		invoiceStates = bs.invoiceStates().list();
		
		bs.paymentGatewayProviders().create(new PaymentGatewayProvider() {{
			setName("braintree");
			setTitle("Braintree");
			setDescription("Braintree Payments");
			setDefault(Boolean.TRUE);
			setProperties(new HashMap<String, String>() {{
				put("k.1", "v.1");
				put("k.2", "v.2");
			}});
		}});
		
		paymentGatewayProviders = bs.paymentGatewayProviders().list();
		
		paymentMethod = bs.paymentGatewayProvider(paymentGatewayProviders.get(0).getId()).paymentMethods().create(new PaymentMethod() {{
			setType("creditcard");
			setName("visa");
			setTitle("VISA");
			setProperties(new HashMap<String, Object>(){{
				put("k.1", "v.1");
				put("k.2", "v.2");
			}});
		}});
		
	}
	
	@After
	public void after() {
		for(Role role : roles) {
			bs.role(role.getId()).delete();
		}
		for(Language language : languages) {
			bs.language(language.getName()).delete();
		}
		for(Currency currency : currencies) {
			bs.currency(currency.getName()).delete();
		}
		for(InvoiceState invoiceState : invoiceStates) {
			bs.invoiceState(invoiceState.getName());
		}
		for(PaymentGatewayProvider paymentGatewayProvider : paymentGatewayProviders) {
			bs.paymentGatewayProvider(paymentGatewayProvider.getId()).delete();
		}
		bs.close();
	}
	
	

}
