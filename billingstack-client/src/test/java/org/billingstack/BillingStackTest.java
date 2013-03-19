package org.billingstack;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
/**
 * 
 * This is the base class for all the tests
 * 
 * Before each test the master data is created
 * 
 * @author sp
 *
 */
public abstract class BillingStackTest {

	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	private static BillingStack client;
	
	protected static BillingStackEndpoint bs;
	
	protected static List<Role> roles;
	
	protected static List<Language> languages;
	
	protected static List<Currency> currencies;
	
	protected static List<InvoiceState> invoiceStates;
	
	protected static List<PaymentGatewayProvider> paymentGatewayProviders;
	
	protected static PaymentMethod paymentMethod;
	
	@BeforeClass
	public static void beforeClass() {
		
		client = new BillingStack();
		
		bs = client.create(ENDPOINT);
		
		//we create 3 different roles for testing purposes
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
		
		//this should be changed as in python stuff, plugin approach!
		
		//since i have not done yet, then we can go as i have now!
		
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
		
		//actually a paymentgatewaprovider which has one visa payment method
	}
	
	@AfterClass
	public static void afterClass() {
		
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
		
		client.close();
	}
	
	@Before
	public void before() {
	}
	
	/**
	 * After each test i drop ALL the data in order to isolate as much as possible
	 */
	@After
	public void after() {
		
	}
	
	

}
