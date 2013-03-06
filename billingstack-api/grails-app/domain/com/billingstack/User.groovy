package com.billingstack

class User {
	
	String id
	
	String username
	
	String password
	
	String apiKey
	String apiSecret

	static belongsTo = [
		merchant : Merchant,
		customer : Customer
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
	}

	static constraints = {
		username(unique : ['merchant','customer'])
		password()
		apiKey(nullable : true)
		apiSecret(nullable : true)
		merchant(nullable : true)
		customer(nullable : true)
	}

}
