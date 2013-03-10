package com.billingstack

class Plan {
	
	String id
	
	String name
	
	String title
	
	String description
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [
		items : PlanItem
	]

	static belongsTo = [
		merchant : Merchant
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		name()
		title(nullable : true)
		description(nullable : true)
	}

}
