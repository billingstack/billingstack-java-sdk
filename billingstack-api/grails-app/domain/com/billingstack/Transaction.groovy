package com.billingstack

class Transaction {
	
	String id
	
	Merchant merchant
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		merchant : Merchant
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		merchant()
	}

}
