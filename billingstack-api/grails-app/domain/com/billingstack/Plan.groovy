package com.billingstack

class Plan {
	
	String id
	
	String name
	
	String title
	
	String description
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		merchant : Merchant
	]
	
	static hasMany = [
		items : PlanItem
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		version false
	}

	static constraints = {
		name()
		title(nullable : true)
		description(nullable : true)
	}

}
