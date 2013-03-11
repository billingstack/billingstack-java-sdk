package com.billingstack

class Customer {
	
	String id
	
	String name
	
	String title
	
	Language language

	Currency currency
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		merchant : Merchant
	]

	static hasMany = [
		metadata : Metadata,
		paymentMethods : PaymentMethod,
		subscriptions : Subscription
	]
	
	static mapping = {
		id generator : "assigned"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		version false
	}

	static constraints = {
		id(bindable : true)
		name()
		title(nullable : true)
		currency(nullable : true)
		language(nullable : true)
		merchant()
	}

}
