package com.billingstack

class InvoiceLine extends BillingStackEntity {
	
	String description
	BigDecimal quantity
	BigDecimal price
	BigDecimal subtotal

	static belongsTo = [
		invoice : Invoice
	]

	static constraints = {
		description()
		quantity()
		price()
		subtotal()
	}

}
