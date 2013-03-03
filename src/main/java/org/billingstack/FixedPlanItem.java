package org.billingstack;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("fixed")
public class FixedPlanItem extends PlanItem {
	
	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
