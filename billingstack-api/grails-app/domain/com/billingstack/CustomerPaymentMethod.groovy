package com.billingstack

class CustomerPaymentMethod {
	
		String id
	
		String name
		
		String identifier
		
		String expires
		
		String properties
	
		PaymentMethod method

		Date dateCreated
		
		Date lastUpdated

		static belongsTo = [
			customer : Customer
		]
		
		static mapping = {
			id generator : "uuid"
			method column : 'provider_method_id'
			dateCreated column : 'created_at'
			lastUpdated column : 'updated_at'
			properties type : 'text'
			version false
		}

		static constraints = {
			name(nullable : true)
			identifier(nullable : true)
			expires(nullable : true)
			properties(nullable : true)
			method()
		}
		
}
