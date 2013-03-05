package org.billingstack;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usage {
	
	private String id;

	@JsonProperty("subscription_id")
	private String subscription;
	
	@JsonProperty("product_id")
	private String product;
	
	private BigDecimal volume;
	
	private String start;
	
	private String end;
	
	private BigDecimal price;
	
	private BigDecimal total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Usage [id=" + id + ", subscription=" + subscription
				+ ", product=" + product + ", volume=" + volume + ", start="
				+ start + ", end=" + end + ", price=" + price + ", total="
				+ total + "]";
	}
	
}
