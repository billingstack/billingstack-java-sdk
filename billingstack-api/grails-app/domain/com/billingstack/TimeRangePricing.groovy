package com.billingstack

class TimeRangePricing extends Pricing {

	TimePricingPlanItem item

	String start

	String end

	BigDecimal price

	static belongsTo = [
		item : TimePricingPlanItem
	]

	static constraints = {
		item()
		start()
		end()
		price()
	}

}
