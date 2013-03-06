package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class MerchantTarget {
	
	private WebTarget target;

	public MerchantTarget(WebTarget target, String merchantId) {
		this.target = target.path("merchants").path(merchantId);
	}
	
	public Merchant show() {
		return target.request().get(Merchant.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	public UsersTarget users() {
		return new UsersTarget(target);
	}
	
	public UserTarget user(String userId) {
		return new UserTarget(target, userId);
	}
	
	public ProductsTarget products() {
		return new ProductsTarget(target);
	}
	
	public ProductTarget product(String productId) {
		return new ProductTarget(target, productId);
	}
	
	public PlansTarget plans() {
		return new PlansTarget(target);
	}
	
	public PlanTarget plan(String planId) {
		return new PlanTarget(target, planId);
	}
	
	public CustomersTarget customers() {
		return new CustomersTarget(target);
	}
	
	public CustomerTarget customer(String customerId) {
		return new CustomerTarget(target, customerId);
	}
	
	public PaymentGatewaysTarget paymentGateways() {
		return new PaymentGatewaysTarget(target);
	}
	
	public PaymentGatewayTarget paymentGateway(String paymentGatewayId) {
		return new PaymentGatewayTarget(target, paymentGatewayId);
	}
	
}