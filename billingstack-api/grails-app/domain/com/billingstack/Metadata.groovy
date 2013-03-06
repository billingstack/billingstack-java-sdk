package com.billingstack

class Metadata implements Serializable {
	
	BillingStackEntity entity
	
	String key
	
	String value
	
	static belongsTo = [
		entity : BillingStackEntity
	]

	static mapping = {
		id composite : ['entity', 'key']
	}

	static constraints = {
			key()
			value(nullable : true)
	}

}
