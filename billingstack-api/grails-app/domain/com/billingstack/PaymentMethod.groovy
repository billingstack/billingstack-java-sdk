package com.billingstack

class PaymentMethod extends BillingStackEntity {
	
	String type
	
	String title
	
	String name
	
	String metadataJson

	static belongsTo = [
		provider : PaymentGatewayProvider
	]
	
	static mapping = {
		metadataJson column : 'properties'
	}

	static constraints = {
		type()
		name()
		title()
		metadataJson()
	}

}
