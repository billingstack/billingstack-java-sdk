package com.billingstack

abstract class PlanItem extends BillingStackEntity {
	
	Product product

	String title

	String description

	static belongsTo = [
		plan : Plan
	]

	static constraints = {
		product()
		title(nullable : true)
		description(nullable :true)
	}

}
