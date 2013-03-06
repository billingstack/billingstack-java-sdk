package com.billingstack

class Merchant extends BillingStackEntity {
	
	String name
	
	String title
	
	Language language
	
	Currency currency

	static hasMany = [
		paymentGateways : PaymentGateway,
		customers : Customer,
		invoices : Invoice,
		products : Product
	]

	static constraints = {
		name()
		title(nullable : true)
		language(nullable : true)
		currency(nullable : true)
	}

}
