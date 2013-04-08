package org.billingstack;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("fixed")
public class FixedPricing {
	
	private BigDecimal price;

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
