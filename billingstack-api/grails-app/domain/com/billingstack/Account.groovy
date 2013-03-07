package com.billingstack

class Account extends BillingStackEntity {
	
		String name

		String title
		
		static hasMany = [
			accountUserRoles : AccountUserRole
		]

    static constraints = {
			name()
			title(nullable : true)
    }
}
