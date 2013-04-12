package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGateway;
import org.billingstack.User;

public class MerchantsExample {
	
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
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
