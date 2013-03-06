package com.billingstack

class TimePricingPlanItem extends PlanItem {

	static hasMany = [
		pricing : TimeRangePricing
	]

}
