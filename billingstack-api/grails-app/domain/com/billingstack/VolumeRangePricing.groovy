package com.billingstack

class VolumeRangePricing extends Pricing {

	VolumePricingPlanItem item

	BigDecimal start

	BigDecimal end

	BigDecimal price

	static belongsTo = [
		item : VolumePricingPlanItem
	]

	static constraints = {
		item()
		start(nullable : true)
		end(nullable : true)
		price()
	}

}
