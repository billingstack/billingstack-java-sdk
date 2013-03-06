package com.billingstack

class UserRole {

	static belongsTo = [
		user : User,
		role : Role
	]

	static constraints = {
		id composite: ['user', 'role']
	}

}
