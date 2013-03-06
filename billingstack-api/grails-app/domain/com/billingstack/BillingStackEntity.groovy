package com.billingstack

class BillingStackEntity {

	String id
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [
		metadata : Metadata
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
		tablePerHierarchy false
	}

	static constraints = {
		metadata nullable : true
	}

}
