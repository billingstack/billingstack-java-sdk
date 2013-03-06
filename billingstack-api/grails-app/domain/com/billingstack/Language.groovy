package com.billingstack

class Language {
	
	String id
	
	String name
	
	String title
	
	static mapping = {
		id generator : "uuid", type : "string"
	}

	static constraints = {
		name()
		title(nullable : true)
	}

}