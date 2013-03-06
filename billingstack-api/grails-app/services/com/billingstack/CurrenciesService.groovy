package com.billingstack

class CurrenciesService {
	
	def map(currency) {
		[
			id : currency.id,
			name : currency.name,
			title : currency.title
		]
	}

	def list() {
		Currency.list().collect { map(it) }
	}

	def create(entity) {
		def currency = Currency.newInstance(
			name : entity.name,
			title : entity.title
		)
		map(currency.save(failOnError : true))
	}

	def show(String currencyId) {
		map(Currency.get(currencyId))
	}

	def update(entity) {
		def currency = Currency.get(entity.id)
		currency
	}

	def delete(String currencyId) {
		Currency.load(currencyId).delete(flush : true)
	}

}
