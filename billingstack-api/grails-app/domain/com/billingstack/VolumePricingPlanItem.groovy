package com.billingstack

class VolumePricingPlanItem extends PlanItem {

		static hasMany = [
			pricing : VolumeRangePricing
		]

    static constraints = {
    }
}
