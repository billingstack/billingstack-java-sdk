package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class ProductTarget {
	
	private WebTarget target;

	public ProductTarget(WebTarget target, String productId) {
		this.target = target.path("products").path(productId);
	}
	
	public Product show() {
		return target.request().get(Product.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
