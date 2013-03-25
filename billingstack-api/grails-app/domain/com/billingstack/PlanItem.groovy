package com.billingstack

class PlanItem implements Serializable {
	
	Product product

	String title

	String description
	
	Date dateCreated
	Date lastUpdated
	
	String pricingJson

	static belongsTo = [
		plan : Plan
	]
	
	static mapping = {
		id composite: ['plan','product']
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		pricingJson type: 'text'
	}

	static constraints = {
		product()
		title(nullable : true)
		description(nullable :true)
		pricingJson()
	}

}
