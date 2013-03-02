package org.billingstack;

import java.math.BigDecimal;

public class FixedPlanItem extends PlanItem {
	
	private static final String TYPE = "fixed";

	public FixedPlanItem() {
		super(TYPE);
	}

	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
