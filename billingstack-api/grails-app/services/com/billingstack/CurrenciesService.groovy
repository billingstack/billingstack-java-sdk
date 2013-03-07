package com.billingstack

class CurrenciesService {
	
	def map(currency) {
		[
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

	def show(String currencyName) {
		map(Currency.get(currencyName))
	}

	def update(entity) {
		def currency = Currency.get(entity.id)
		currency
	}

	def delete(String currencyName) {
		Currency.load(currencyName).delete(flush : true)
	}

}
