package com.billingstack

class Currency {
	
	String name
	
	String title
	
	static mapping = {
		id name : "name", generator : "assigned"
		version false
	}

	static constraints = {
		name()
		title(nullable : true)
	}

}
