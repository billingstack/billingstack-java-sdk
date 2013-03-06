package com.billingstack

class CustomerPaymentMethodsService {

	def map(customerCustomerPaymentMethod) {
		[
			id : customerCustomerPaymentMethod.id,
			customer : customerCustomerPaymentMethod.customer.id
		]
	}

	def list() {
		CustomerPaymentMethod.list().collect { map(it) }
	}

	def create(customerId, entity) {
		def customerCustomerPaymentMethod = CustomerPaymentMethod.newInstance(
			customer : Customer.load(customerId),
			method : PaymentMethod.load(entity.method_id)
		)
		map(customerCustomerPaymentMethod.save(failOnError : true))
	}

	def show(String customerCustomerPaymentMethodId) {
		map(CustomerPaymentMethod.get(customerCustomerPaymentMethodId))
	}

	def update(entity) {
		def customerCustomerPaymentMethod = CustomerPaymentMethod.get(entity.id)
		map(customerCustomerPaymentMethod)
	}

	def delete(String customerCustomerPaymentMethodId) {
		CustomerPaymentMethod.load(customerCustomerPaymentMethodId).delete(flush : true)
	}

}
