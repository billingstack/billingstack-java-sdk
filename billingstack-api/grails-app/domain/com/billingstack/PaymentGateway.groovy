package com.billingstack

class PaymentGateway {
	
	String id
	
	Merchant merchant
	
	String title
	
	PaymentGatewayProvider provider
	
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [
		merchant : Merchant
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		merchant()
		title(nullable : true)
		provider()
	}

}
