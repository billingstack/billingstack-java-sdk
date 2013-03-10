package com.billingstack

class Account {
	
		String id
	
		String type
	
		String name

		String title
		
		Date dateCreated
		
		Date lastUpdated
		
		static hasMany = [
			metadata : Metadata,
			accountUserRoles : AccountUserRole
		]
		
		static mapping = {
			id generator : "uuid", type : "string"
			dateCreated column : 'created_at'
			lastUpdated column : 'updated_at'
			version false
		}

    static constraints = {
			type(nullable : true)
			name()
			title(nullable : true)
    }
}
