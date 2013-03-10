package com.billingstack

class InvoiceLine {
	
	String id
	
	String description
	BigDecimal quantity
	BigDecimal price
	BigDecimal subtotal
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		invoice : Invoice
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		version false
	}

	static constraints = {
		description()
		quantity()
		price()
		subtotal()
	}

}
