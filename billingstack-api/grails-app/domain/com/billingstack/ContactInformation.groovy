package com.billingstack

class ContactInformation {
	
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
