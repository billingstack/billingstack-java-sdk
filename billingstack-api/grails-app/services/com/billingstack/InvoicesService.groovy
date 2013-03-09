package com.billingstack

class InvoicesService {

	def map(invoice) {
		[
			id : invoice.id,
			merchant : invoice.merchant.id,
			customer : invoice.customer.id,
			identifier : invoice.identifier,
			due : invoice.due,
			subTotal : invoice.subtotal,
			taxPercentage : invoice.taxPercentage,
			taxTotal : invoice.taxTotal,
			total : invoice.total,
			state : invoice.state,
			currency : invoice.currency.id
		]
	}

	def list() {
		Invoice.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def invoice = Invoice.newInstance(
			merchant : Merchant.load(merchantId),
			customer : Customer.load(entity.customer_id),
			identifier : entity.identifier,
			due : entity.due,
			subTotal : entity.subtotal,
			taxPercentage : entity.tax_percentage,
			taxTotal : entity.tax_total,
			total : entity.total,
			state : entity.state,
			currency : Currency.load(entity.currency)
		)
		map(invoice.save(failOnError : true, flush : true))
	}

	def show(String invoiceId) {
		map(Invoice.get(invoiceId))
	}

	def update(entity) {
		def invoice = Invoice.get(entity.id)
		map(invoice)
	}

	def delete(String invoiceId) {
		Invoice.load(invoiceId).delete(flush : true)
	}

}
