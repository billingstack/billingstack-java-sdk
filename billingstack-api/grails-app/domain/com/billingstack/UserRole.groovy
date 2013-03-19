package com.billingstack

class UserRole implements Serializable {
	
		static belongsTo = [
			user : User,
			role : Role
		]

		static mapping = {
			table "user_roles"
			id composite: ['user','role']
			version false
		}

}
