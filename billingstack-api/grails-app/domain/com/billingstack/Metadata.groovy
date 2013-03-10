package com.billingstack

class Metadata {
	
	String key
	
	String value
	
	static belongsTo = [
		Account, Merchant, Customer
	]
	
	static mapping = {
		version false
	}

	static constraints = {
			key()
			value(nullable : true)
	}

}
