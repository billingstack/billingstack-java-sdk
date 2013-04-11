package com.billingstack

class InvoiceLinesService {

	def map(invoiceLine) {
		[
			id : invoiceLine.id,
			invoice_id : invoiceLine.invoice.id,
			description : invoiceLine.description,
			quantity : invoiceLine.quantity,
			price : invoiceLine.price,
			subtotal : invoiceLine.subtotal
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
			price : entity.price
		)
		invoiceLine.subtotal = invoiceLine.quantity * invoiceLine.price
		map(invoiceLine.save(failOnError : true))
	}

	def show(String invoiceLineId) {
		map(InvoiceLine.get(invoiceLineId))
	}

	def update(String merchantId, String invoiceId, String invoiceLineId, entity) {
		def invoiceLine = InvoiceLine.get(invoiceLineId)
		invoiceLine.description = entity.description
		invoiceLine.quantity = entity.quantity
		invoiceLine.price = entity.price
		map(invoiceLine)
	}

	def delete(String invoiceLineId) {
		InvoiceLine.load(invoiceLineId).delete(flush : true)
	}

}
