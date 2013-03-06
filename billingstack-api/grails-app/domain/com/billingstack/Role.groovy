package com.billingstack

class Role {
	
	String id

	String name

	static mapping = {
		id generator : "uuid", type : "string"
	}
	
	static constraints = {
		name()
	}

}
