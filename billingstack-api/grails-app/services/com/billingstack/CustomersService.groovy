package com.billingstack

import grails.converters.JSON

class CustomersService {

	def map(customer) {
		def entity = [
			id : customer.id,
			merchant_id : customer.merchant.id,
			name : customer.name,
			title : customer.title
		]
		if(customer.language) entity.language = customer.language.name
		if(customer.currency) entity.currency = customer.currency.name
		entity
	}

	def list() {
		Customer.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def customer = Customer.newInstance(
			id : entity.id,
			merchant : Merchant.load(merchantId),
			name : entity.name,
			title : entity.title,
			language : Language.load(entity.language),
			currency : Currency.load(entity.currency)
		)
		/*
		customer.metadata = entity.metadata.collect { k, v ->
			Metadata.newInstance(key : k, value : v)
		}
		*/
		map(customer.save(failOnError : true))
	}

	def show(String merchantId, String customerId) {
		def customer = Customer.get(customerId)
		if(!customer) {
			throw new RuntimeException("not.found")
		}
		map(customer)
	}

	def update(entity) {
		def customer = Customer.get(entity.id)
		map(customer)
	}

	def delete(String customerId) {
		Customer.load(customerId).delete(flush : true)
	}

}
