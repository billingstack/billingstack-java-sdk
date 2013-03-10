package com.billingstack

class Role {
	
	String id

	String name
	
	Strign extra
	
	Date dateCreated
	
	Date lastUpdated

	static mapping = {
		id generator : "uuid", type : "string"
		extra type: 'text'
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
	}
	
	static constraints = {
		name()
		extra(nullable : true)
	}

}
