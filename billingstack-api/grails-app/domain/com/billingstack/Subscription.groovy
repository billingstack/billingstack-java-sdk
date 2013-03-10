package com.billingstack

class Subscription {
	
	String id
	
	Plan plan
	
	String resource
	
	CustomerPaymentMethod paymentMethod
	
	Integer billingDay
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		customer : Customer
	]
	
	static hasMany = [
		usage : Usage
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		plan()
		resource(nullable : true)
		paymentMethod(nullable : true)
		billingDay()
	}

}
