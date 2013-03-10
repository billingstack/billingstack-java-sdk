package com.billingstack

abstract class PlanItem {
	
	String id
	
	Product product

	String title

	String description
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		plan : Plan
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		product()
		title(nullable : true)
		description(nullable :true)
	}

}
