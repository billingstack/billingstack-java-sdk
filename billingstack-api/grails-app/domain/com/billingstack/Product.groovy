package com.billingstack

class Product extends BillingStackEntity {
	
	String provider
	
	String source
	
	String name
	
	String title
	
	String description
	
	static belongsTo = [
		merchant : Merchant
	]

	static constraints = {
		provider(nullable : true)
		source(nullable : true)
		name()
		title(nullable : true)
		description(nullable : true)
	}

}
