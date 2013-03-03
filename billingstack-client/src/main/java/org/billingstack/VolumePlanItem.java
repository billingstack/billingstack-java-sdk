package org.billingstack;

import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("volume")
public class VolumePlanItem extends PlanItem {

	private List<VolumeRangePricing> pricing;

	public List<VolumeRangePricing> getPricing() {
		return pricing;
	}

	public void setPricing(List<VolumeRangePricing> pricing) {
		this.pricing = pricing;
	}
	
}
