package com.billingstack

class Merchant {
	
	String id
	
	String name
	
	String title
	
	Language language

	Currency currency
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [
		metadata : Metadata,
		paymentGateways : PaymentGateway,
		products : Product,
		plans : Plan,
		customers : Customer,
		invoices : Invoice
	]

	static mapping = {
		id generator : "assigned"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		id(bindable : true)
		name()
		title(nullable : true)
		language()
		currency()
	}

}
