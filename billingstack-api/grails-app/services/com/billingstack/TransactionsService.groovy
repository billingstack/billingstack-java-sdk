package com.billingstack

class TransactionsService {
	
	def invoicesService

	def map(transaction) {
		[
			id : transaction.id,
			merchant : transaction.merchant.id,
			amount : transaction.amount,
			status : transaction.status,
			invoices : transaction.invoices.collect { it.id },
			date_created : transaction.dateCreated
		]
	}
	
	def list(String merchantId, filters) {
		def ops = filters.list('q.op')
		def names = filters.list('q.field')
		def values = filters.list('q.value')
		Transaction.createCriteria().list {
			customer {
				eq 'merchant.id', merchantId
			}
			for(int i = 0; i < ops.size(); i++) {
				if("customer_id".equals(names.get(i))) {
					customer {
						"${ops.get(i)}"('id',values.get(i))
					}
				}
			}
		}.collect { map(it) }
	}

	def create(merchantId, entity) {
		def invoices = []
		entity.invoices.each { 
			invoices << Invoice.get(it)
		}
		def amount = invoices.sum { it.subtotal }
		def transaction = Transaction.newInstance(
			merchant : Merchant.load(merchantId),
			customer : Customer.load(entity.customer_id),
			amount : amount,
			status : "PENDING"
		).save(failOnError : true, flush : true)
		invoices.each {
			it.transaction = transaction
		}
		map(transaction)
	}

	def show(String transactionId) {
		map(Transaction.get(transactionId))
	}

	def update(entity) {
		def transaction = Transaction.get(entity.id)
		map(transaction)
	}

	def delete(String transactionId) {
		Transaction.load(transactionId).delete(flush : true)
	}

}
