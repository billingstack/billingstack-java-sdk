package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class CustomerTarget {
	
	private WebTarget target;

	public CustomerTarget(WebTarget target, String customerId) {
		this.target = target.path("customers").path(customerId);
	}
	
	public Customer show() {
		return target.request().get(Customer.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	public CustomerPaymentMethodsTarget paymentMethods() {
		return new CustomerPaymentMethodsTarget(target);
	}
	
	public CustomerPaymentMethodTarget paymentMethod(String customerPaymentMethodId) {
		return new CustomerPaymentMethodTarget(target, customerPaymentMethodId);
	}

	public InvoicesTarget invoices() {
		return new InvoicesTarget(target);
	}
	
	public InvoiceTarget invoice(String invoiceId) {
		return new InvoiceTarget(target, invoiceId);
	}
	
	public UsagesTarget usages() {
		return new UsagesTarget(target);
	}
	
	public UsageTarget usage(String usageId) {
		return new UsageTarget(target, usageId);
	}
	
}
