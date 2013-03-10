package com.billingstack

class Usage {
	
	String id
	
	Subscription subscription
	
	Product product
	
	BigDecimal volume
	
	Long start
	
	Long end
	
	BigDecimal price
	
	BigDecimal total
	
	Date dateCreated
	Date lastUpdated

	static belongsTo = [
		subscription : Subscription
	]
	
	static mapping = {
		id generator : "uuid"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}

	static constraints = {
		subscription()
		product()
		volume()
		start()
		end()
		price(nullable : true)
		total(nullable : true)
	}

}
