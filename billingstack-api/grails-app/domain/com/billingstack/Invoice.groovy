package com.billingstack

class Invoice {
	
	String id
	
	Customer customer
	
	String identifier
	
	Date due
	
	BigDecimal subtotal
	BigDecimal taxPercentage
	BigDecimal taxTotal
	BigDecimal total
	
	InvoiceState state
	
	Currency currency
	
	Transaction transaction
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		merchant : Merchant
	]
	
	static hasMany = [
		lines : InvoiceLine
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		version false
	}

	static constraints = {
		customer()
		identifier(nullable : true)
		due(nullable : true)
		subtotal(nullable : true)
		taxPercentage(nullable : true)
		taxTotal(nullable : true)
		total(nullable : true)
		state(nullable : true)
		currency(nullable : true)
		transaction(nullable : true)
		dateCreated()
		lastUpdated()
	}

}
