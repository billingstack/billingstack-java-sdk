package com.billingstack

class Product {
	
	String id
	
	String name
	
	String title
	
	String description
	
	String metadataJson
	
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [
		merchant : Merchant
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		metadataJson column : 'properties'
	}

	static constraints = {
		name()
		title(nullable : true)
		description(nullable : true)
		metadataJson(nullable : true)
	}

}
