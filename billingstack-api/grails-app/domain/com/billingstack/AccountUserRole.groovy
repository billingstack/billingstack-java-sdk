package com.billingstack

class AccountUserRole implements Serializable {
	
		static belongsTo = [
			user : User,
			account : Account,
			role : Role
		]

		static mapping = {
			table "user_account_grant"
			id composite: ['user','account','role']
			version false
		}

}
