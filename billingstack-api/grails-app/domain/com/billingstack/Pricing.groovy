package com.billingstack

abstract class Pricing {
	
		String id
		
		static mapping = {
			id generator : "uuid", type : "string"
			tablePerHierarchy false
		}

    static constraints = {
    }
}
