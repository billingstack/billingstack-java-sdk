package com.billingstack

class CustomerPaymentMethod {
	
		PaymentMethod method

		static belongsTo = [
			customer : Customer
		]

		static constraints = {
			method()
		}
		
}
