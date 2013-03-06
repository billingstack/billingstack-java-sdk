package com.billingstack

class InvoicesService {

	def map(invoice) {
		[
			id : invoice.id,
			merchant : invoice.merchant.id,
			customer : invoice.customer.id,
			identifier : invoice.identifier,
			due : invoice.due,
			subTotal : invoice.subTotal,
			taxPercentage : invoice.taxPercentage,
			taxTotal : invoice.taxTotal,
			total : invoice.total,
			status : invoice.status,
			currency : invoice.currency.id
		]
	}

	def list() {
		Invoice.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def invoice = Invoice.newInstance(
			merchant : Merchant.load(merchantId),
			customer : Customer.load(entity.customer),
			identifier : entity.identifier,
			due : entity.due,
			subTotal : entity.subTotal,
			taxPercentage : entity.taxPercentage,
			taxTotal : entity.taxTotal,
			total : entity.total,
			status : entity.status,
			currency : Currency.findByIdOrName(entity.currency, entity.currency)
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
