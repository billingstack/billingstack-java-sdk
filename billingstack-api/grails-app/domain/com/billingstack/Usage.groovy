package com.billingstack

class Usage extends BillingStackEntity {
	
	Subscription subscription
	
	Product product
	
	BigDecimal volume
	
	Long start
	
	Long end
	
	BigDecimal price
	
	BigDecimal total

	static belongsTo = [
		subscription : Subscription
	]

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
