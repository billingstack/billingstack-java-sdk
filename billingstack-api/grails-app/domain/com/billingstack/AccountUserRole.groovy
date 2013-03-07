package com.billingstack

class AccountUserRole implements Serializable {
	
		static belongsTo = [
			user : User,
			account : Account,
			role : Role
		]

		static mapping = {
			id composite: ['user','account','role']
			version false
		}

}
