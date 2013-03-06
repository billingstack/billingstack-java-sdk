package com.billingstack

class Invoice extends BillingStackEntity {
	
	Customer customer
	
	String identifier
	
	Date due
	
	BigDecimal subTotal
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
		identifier()
		due()
		subTotal()
		taxPercentage()
		taxTotal()
		total()
		state()
		currency()
		transaction(nullable : true)
	}

}
