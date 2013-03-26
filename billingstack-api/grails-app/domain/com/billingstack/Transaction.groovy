package com.billingstack

class Transaction {
	
	String id
	
	Merchant merchant
	
	Customer customer

	BigDecimal amount

	String status
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		merchant : Merchant
	]

	static hasMany = [
		invoices : Invoice
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		version false
	}

	static constraints = {
		merchant()
		customer()
		amount()
		status(nullable : true)
	}

}
