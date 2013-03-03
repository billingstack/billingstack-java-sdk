package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class ProductsTarget {

	private WebTarget target;

	public ProductsTarget(WebTarget target) {
		this.target = target.path("products");
	}
	
	public List<Product> list() {
		return target.request().get(new GenericType<List<Product>>(){});
	}
	
	public Product create(Product product) {
		return target.request().post(Entity.json(product),Product.class);
	}
	
}
