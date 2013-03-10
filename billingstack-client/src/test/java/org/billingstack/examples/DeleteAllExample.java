package org.billingstack.examples;

import org.billingstack.BillingStack;

public class DeleteAllExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingStack bs = new BillingStack(ENDPOINT);
		
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
		
		bs.close();
	}

}
