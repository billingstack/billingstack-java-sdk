package com.billingstack

class PaymentMethod {
	
	String id
	
	String type
	
	String title
	
	String name
	
	String metadataJson
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		provider : PaymentGatewayProvider
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		metadataJson column : 'properties'
	}

	static constraints = {
		type()
		name()
		title()
		metadataJson(nullable : true)
	}

}
