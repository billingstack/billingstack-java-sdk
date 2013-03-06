package com.billingstack

class Transaction extends BillingStackEntity {
	
	Merchant merchant

	static belongsTo = [
		merchant : Merchant
	]

	static constraints = {
		merchant()
	}

}
