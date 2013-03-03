package org.billingstack;

import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("time")
public class TimePlanItem extends PlanItem {

	private List<TimeRangePricing> pricing;

	public List<TimeRangePricing> getPricing() {
		return pricing;
	}

	public void setPricing(List<TimeRangePricing> pricing) {
		this.pricing = pricing;
	}
	
}
