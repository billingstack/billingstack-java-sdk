package org.billingstack.examples;

import java.util.HashMap;
import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGateway;
import org.billingstack.User;

public class MerchantsExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack client = new BillingStack();
		BillingStackEndpoint bs = client.create(ENDPOINT);
		
		Merchant merchant = bs.merchants().create(new Merchant() {{
			setName("billingstack");
			setTitle("BillingStack");
			setLanguage("en");
			setCurrency("usd");
		}});
		
		User user = bs.merchant(merchant.getId()).users().create(new User() {{
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		bs.merchant(merchant.getId()).user(user.getId()).role(bs.roles().list().get(1).getId()).create();
		
		final List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		m.show();
		
		final List<User> merchantUsers = bs.merchant(merchants.get(0).getId()).users().list();
	
		m.user(merchantUsers.get(0).getId()).show();
		
		PaymentGateway pg = m.paymentGateways().create(new PaymentGateway() {{
			setProvider("braintree");
			setTitle("My Braintree");
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<PaymentGateway> paymentGateways = m.paymentGateways().list();
		
		m.paymentGateway(paymentGateways.get(0).getId()).show();
		
		client.close();

	}

}
