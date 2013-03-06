package com.billingstack

class PaymentGateway extends BillingStackEntity {
	
	Merchant merchant
	
	String title
	
	PaymentGatewayProvider provider
	
	static belongsTo = [
		merchant : Merchant
	]

	static constraints = {
		merchant()
		title(nullable : true)
		provider()
	}

}
