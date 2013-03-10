package com.billingstack

class Product {
	
	String id
	
	String provider
	
	String source
	
	String name
	
	String title
	
	String description
	
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [
		merchant : Merchant
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		provider(nullable : true)
		source(nullable : true)
		name()
		title(nullable : true)
		description(nullable : true)
	}

}
