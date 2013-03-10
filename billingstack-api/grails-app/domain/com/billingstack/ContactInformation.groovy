package com.billingstack

class ContactInformation {
	
		String id
	
		String firstName
		String lastName
		String company
		String address1
		String address2
		String locality
		String region
		String country
		String postalCode
		
		String phone
		String email
		String website
		
		Date dateCreated
		Date lastUpdated
		
		static mapping = {
			table "contact_info"
			id generator : "uuid", type : "string"
			dateCreated column : 'created_at'
			lastUpdated column : 'updated_at'
			version false
		}

    static constraints = {
			firstName()
			lastName()
			company()
			address1()
			address2()
			locality()
			region()
			country()
			postalCode()
			phone()
			email()
			website()
    }
}
