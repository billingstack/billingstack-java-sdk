package com.billingstack

class Plan extends BillingStackEntity {
	
	String name
	
	String title
	
	String description
	
	static hasMany = [
		items : PlanItem
	]

	static belongsTo = [
		merchant : Merchant
	]

	static constraints = {
		name()
		title(nullable : true)
		description(nullable : true)
	}

}
