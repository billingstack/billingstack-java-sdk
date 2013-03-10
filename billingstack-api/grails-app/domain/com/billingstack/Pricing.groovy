package com.billingstack

abstract class Pricing {
	
		String id
		
		static mapping = {
			id generator : "uuid"
			tablePerHierarchy false
		}

    static constraints = {
    }
}
