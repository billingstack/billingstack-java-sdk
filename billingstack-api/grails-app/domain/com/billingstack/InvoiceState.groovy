package com.billingstack

class InvoiceState {

    String id

		String name

		static mapping = {
			id generator : "uuid", type : "string"
			version false
		}

		static constraints = {
			name()
		}

}
