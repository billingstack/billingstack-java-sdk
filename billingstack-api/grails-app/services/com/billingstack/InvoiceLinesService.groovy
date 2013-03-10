package com.billingstack

class InvoiceLinesService {

	def map(invoiceLine) {
		[
			id : invoiceLine.id,
			invoice_id : invoiceLine.invoice.id
		]
	}

	def list() {
		InvoiceLine.list().collect { map(it) }
	}

	def create(invoiceId, entity) {
		def invoiceLine = InvoiceLine.newInstance(
			invoice : Invoice.load(invoiceId),
			description : entity.description,
			quantity : entity.quantity,
			price : entity.price,
			subtotal : entity.subtotal
		)
		map(invoiceLine.save(failOnError : true))
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
