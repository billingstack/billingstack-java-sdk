package com.billingstack

class InvoiceState {

		String name

		static mapping = {
			id name : 'name', generator : "assigned"
			version false
		}

		static constraints = {
			name()
		}

}
