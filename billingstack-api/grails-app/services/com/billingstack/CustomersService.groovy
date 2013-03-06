package com.billingstack

class CustomersService {

	def map(customer) {
		def entity = [
			id : customer.id,
			merchant_id : customer.merchant.id,
			name : customer.name,
			title : customer.title
		]
		/*
		if(customer.language) entity.language_id = customer.language.id
		if(customer.currency) entity.currency_id = customer.currency.id
		*/
		entity
	}

	def list() {
		Customer.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def customer = Customer.newInstance(
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			language : Language.findByIdOrName(entity.language, entity.language),
			currency : Currency.findByIdOrName(entity.currency, entity.currency)
		)
		/*
		customer.metadata = entity.metadata.collect { k, v ->
			Metadata.newInstance(key : k, value : v)
		}
		*/
		map(customer.save(failOnError : true))
	}

	def show(String customerId) {
		map(Customer.get(customerId))
	}

	def update(entity) {
		def customer = Customer.get(entity.id)
		map(customer)
	}

	def delete(String customerId) {
		Customer.load(customerId).delete(flush : true)
	}

}
