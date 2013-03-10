package com.billingstack

class Account extends BillingStackEntity {
	
		String type
	
		String name

		String title
		
		Date dateCreated
		
		Date lastUpdated
		
		static hasMany = [
			accountUserRoles : AccountUserRole
		]
		
		static mapping = {
			dateCreated column : 'created_at'
			lastUpdated column : 'updated_at'
		}

    static constraints = {
			type(nullable : true)
			name()
			title(nullable : true)
    }
}
