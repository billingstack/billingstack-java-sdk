package com.billingstack

class InvoiceLinesService {

	def map(invoiceLine) {
		[
			id : customer.id,
			invoice : invoiceLine.invoice.id
		]
	}

	def list() {
		InvoiceLine.list().collect { map(it) }
	}

	def create(invoiceId, entity) {
		def invoiceLine = InvoiceLine.newInstance(
			merchant : Invoice.load(invoiceId)
		)
		map(invoiceLine.save())
	}

	def show(String invoiceLineId) {
		map(InvoiceLine.get(invoiceLineId))
	}

	def update(entity) {
		def invoiceLine = InvoiceLine.get(entity.id)
		map(invoiceLine)
	}

	def delete(String invoiceLineId) {
		InvoiceLine.load(invoiceLineId).delete(flush : true)
	}

}
