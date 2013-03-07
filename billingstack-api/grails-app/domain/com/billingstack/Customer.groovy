package com.billingstack

class Customer {
	
	String id

	Merchant merchant
	
	Language language

	Currency currency
	
	Date dateCreated
	Date lastUpdated

	static hasMany = [
		metadata : Metadata,
		paymentMethods : PaymentMethod,
		subscriptions : Subscription
	]
	
	static mapping = {
		id generator : "assigned"
	}

	static constraints = {
		id(bindable : true)
		currency(nullable : true)
		language(nullable : true)
	}

}
