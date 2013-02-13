package org.billingstack.examples;

import org.billingstack.BillingStack;
import org.billingstack.Customer;
import org.billingstack.Invoice;
import org.billingstack.Merchant;
import org.billingstack.PaymentGateway;
import org.billingstack.PaymentMethod;
import org.billingstack.Plan;
import org.billingstack.Product;
import org.billingstack.Subscription;
import org.billingstack.User;

public class BillingStackExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingStack bs = new BillingStack();
		bs.merchants().list();
		bs.merchants().create(new Merchant());
		bs.merchant("123").show();
		bs.merchant("123").delete();
		
		bs.merchant("123").users().list();
		bs.merchant("123").users().create(new User());
		bs.merchant("123").user("456").show();
		bs.merchant("123").user("456").delete();
		
		bs.merchant("123").paymentGateways().list();
		bs.merchant("123").paymentGateways().create(new PaymentGateway());
		bs.merchant("123").paymentGateway("456").show();
		bs.merchant("123").paymentGateway("456").delete();
		
		bs.merchant("123").products().list();
		bs.merchant("123").products().create(new Product());
		bs.merchant("123").product("456").show();
		bs.merchant("123").product("456").delete();
		
		bs.merchant("123").plans().list();
		bs.merchant("123").plans().create(new Plan());
		bs.merchant("123").plan("456").show();
		bs.merchant("123").plan("456").delete();
		
		bs.merchant("123").plan("456").subscriptions().list();
		bs.merchant("123").plan("456").subscriptions().create(new Subscription());
		bs.merchant("123").plan("456").subscription("789").show();
		bs.merchant("123").plan("456").subscription("789").delete();
		
		bs.merchant("123").customers().list();
		bs.merchant("123").customers().create(new Customer());
		bs.merchant("123").customer("456").show();
		bs.merchant("123").customer("456").delete();
		
		bs.merchant("123").customer("456").paymentMethods().list();
		bs.merchant("123").customer("456").paymentMethods().create(new PaymentMethod());
		bs.merchant("123").customer("456").paymentMethod("789").show();
		bs.merchant("123").customer("456").paymentMethod("789").delete();
		
		bs.merchant("123").customer("456").users().list();
		bs.merchant("123").customer("456").users().create(new User());
		bs.merchant("123").customer("456").user("789").show();
		bs.merchant("123").customer("456").user("789").delete();
		
		bs.merchant("123").customers().list();
		bs.merchant("123").customers().create(new Customer());
		bs.merchant("123").customer("456").invoices().list();
		bs.merchant("123").customer("456").invoices().create(new Invoice());
		
		bs.merchant("123").customers().list();
		bs.merchant("123").customers().create(new Customer());
		bs.merchant("123").customer("456").subscriptions().list();
		bs.merchant("123").customer("456").subscriptions().create(new Subscription());
		bs.merchant("123").customer("456").subscription("789").show();
		bs.merchant("123").customer("456").subscription("789").delete();
	}

}
