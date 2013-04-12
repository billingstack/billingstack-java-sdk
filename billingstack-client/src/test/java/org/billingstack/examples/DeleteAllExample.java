package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

public class DeleteAllExample {
	
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
		/*
		final List<Merchant> merchants = bs.merchants().list();
		
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
		*/
//		bs.merchant("123").customer("456").invoices().list();
//		bs.merchant("123").customer("456").invoices().create(new Invoice());
//		bs.merchant("123").customer("456").invoice("789").show();
//		bs.merchant("123").customer("456").invoice("789").delete();
		
		client.close();
	}

}
