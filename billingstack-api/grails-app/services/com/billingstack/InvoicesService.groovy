package com.billingstack

class InvoicesService {
	
	def invoiceLinesService

	def map(invoice) {
		def subtotal = invoice.lines ? invoice.lines.sum { it.subtotal } : 0
		def entity = [
			id : invoice.id,
			merchant_id : invoice.merchant.id,
			customer_id : invoice.customer.id,
			customer_name : invoice.customer.name,
			customer_title : invoice.customer.title,
			identifier : invoice.identifier,
			due : invoice.due,
			subtotal : subtotal,
			tax_percentage : invoice.taxPercentage,
			tax_total : invoice.taxTotal,
			total : invoice.total,
			lines : []
			//currency : invoice.currency.id
		]
		if(invoice.transaction) {
			entity.transaction_id = invoice.transaction.id
			entity.transaction_date = invoice.transaction.dateCreated
			entity.state = invoice.transaction.status
		} else {
			entity.state = 'CREATED'
		}
		invoice.lines.each {
			entity.lines << invoiceLinesService.map(it)
		}
		entity
	}

	def list(String merchantId, filters) {
		def f = [
			'merchant.id' : merchantId
		]
		if(filters.customer_id) {
			f['customer.id'] = filters.customer_id
		}
		Invoice.findAllWhere(f).collect { map(it) }
	}

	def create(merchantId, entity) {
		
		def invoice = Invoice.newInstance(
			merchant : Merchant.load(merchantId),
			customer : Customer.load(entity.customer_id),
			identifier : entity.identifier,
			due : entity.due,
			subtotal : entity.subtotal,
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
