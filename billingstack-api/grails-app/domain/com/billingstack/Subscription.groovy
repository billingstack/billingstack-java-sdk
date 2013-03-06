package com.billingstack

class Subscription extends BillingStackEntity {
	
	Plan plan
	
	String resource
	
	CustomerPaymentMethod paymentMethod
	
	Integer billingDay

	static belongsTo = [
		customer : Customer
	]
	
	static hasMany = [
		usage : Usage
	]

	static constraints = {
		plan()
		resource(nullable : true)
		paymentMethod(nullable : true)
		billingDay()
	}

}
