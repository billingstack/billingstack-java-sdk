package com.billingstack

class CustomerPaymentMethod {
	
		String id
		
		String expires
		
		String propertiesJson
	
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
			propertiesJson type : 'text'
			version false
		}

		static constraints = {
			expires(nullable : true)
			propertiesJson(nullable : true)
			method(nullable : true)
		}
		
}
