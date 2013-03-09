package com.billingstack

class User {
	
	String id
	
	String username
	
	String password
	
	String apiKey
	String apiSecret
	
	static hasMany = [
		accountUserRoles : AccountUserRole
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
	}

	static constraints = {
		username(/* unique : true */)
		password()
		apiKey(nullable : true)
		apiSecret(nullable : true)
	}

}
