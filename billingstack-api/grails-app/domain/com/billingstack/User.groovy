package com.billingstack

class User {
	
	String id
	
	String username
	
	String password
	
	//String apiKey
	//String apiSecret
	
	Date dateCreated
	
	Date lastUpdated
	
	static hasMany = [
		accountUserRoles : AccountUserRole
	]
	
	static mapping = {
		id generator : "uuid", type : "string"
		dateCreated column : 'created_at'
		lastUpdated column : 'updated_at'
		version false
	}

	static constraints = {
		username(/* unique : true */)
		password()
		//apiKey(nullable : true)
		//apiSecret(nullable : true)
	}

}
