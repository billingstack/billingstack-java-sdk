package com.billingstack

class Customer extends BillingStackEntity {
	
	String name
	
	String title
	
	Currency currency
	
	Language language

	static belongsTo = [
		merchant : Merchant
	]

	static hasMany = [
		users : User,
		paymentMethods : PaymentMethod,
		subscriptions : Subscription
	]

	static constraints = {
		name()
		title(nullable : true)
		currency(nullable : true)
		language(nullable : true)
	}

}
