package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class ProductsTarget {

	private WebTarget target;

	public ProductsTarget(WebTarget target) {
		this.target = target.path("products");
	}
	
	public Products list() {
		return target.request().get(Products.class);
	}
	
	public Product create(Product product) {
		return target.request().post(Entity.json(product),Product.class);
	}
	
}
