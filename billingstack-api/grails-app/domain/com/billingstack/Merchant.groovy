package com.billingstack

class Merchant {
	
	String id
	
	Language language

	Currency currency
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [
		metadata : Metadata,
		paymentGateways : PaymentGateway,
		customers : Customer,
		invoices : Invoice,
		products : Product
	]
	
	static mapping = {
		id generator : "assigned"
	}

	static constraints = {
		id(bindable : true)
		language()
		currency()
	}

}
