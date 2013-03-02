package org.billingstack;

import java.util.List;

public class VolumePlanItem extends PlanItem {
	
	private static final String TYPE = "volume";

	private List<VolumeRangePricing> pricing;
	
	public VolumePlanItem() {
		super(VolumePlanItem.TYPE);
	}

	public List<VolumeRangePricing> getPricing() {
		return pricing;
	}

	public void setPricing(List<VolumeRangePricing> pricing) {
		this.pricing = pricing;
	}
	
}
