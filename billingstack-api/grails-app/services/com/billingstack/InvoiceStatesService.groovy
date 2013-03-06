package com.billingstack

class InvoiceStatesService {
	
	def map(invoiceState) {
		[
			id : invoiceState.id,
			name : invoiceState.name
		]
	}

	def list() {
		InvoiceState.list().collect { map(it) }
	}

	def create(entity) {
		def invoiceState = InvoiceState.newInstance(
			name : entity.name
		)
		map(invoiceState.save())
	}

	def show(String invoiceStateId) {
		map(InvoiceState.get(invoiceStateId))
	}

	def update(entity) {
		def invoiceState = InvoiceState.get(entity.id)
		map(invoiceState)
	}

	def delete(String invoiceStateId) {
		InvoiceState.load(invoiceStateId).delete(flush : true)
	}

}
