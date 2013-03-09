package com.billingstack

class Invoice extends BillingStackEntity {
	
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

	static belongsTo = [
		merchant : Merchant
	]
	
	static hasMany = [
		lines : InvoiceLine
	]

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
	}

}
