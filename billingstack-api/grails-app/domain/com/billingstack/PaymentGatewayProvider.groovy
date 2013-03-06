package com.billingstack

class PaymentGatewayProvider extends BillingStackEntity {
	
	String name
	
	String title
	
	String description
	
	String metadataJson
	
	static hasMany = [
		methods : PaymentMethod
	]
	
	static mapping = {
		metadataJson column : 'properties'
	}

	static constraints = {
		name()
		title(nullable : true)
		description(nullable :true)
		metadataJson(nullable : true)
	}

}
