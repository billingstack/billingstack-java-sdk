package org.billingstack;

import java.util.List;

public class TimePlanItem extends PlanItem {

	private static final String TYPE = "time";
	
	public TimePlanItem() {
		super(TYPE);
	}

	private List<TimeRangePricing> pricing;

	public List<TimeRangePricing> getPricing() {
		return pricing;
	}

	public void setPricing(List<TimeRangePricing> pricing) {
		this.pricing = pricing;
	}
	
}
