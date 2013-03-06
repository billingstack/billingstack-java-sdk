package com.billingstack

class TransactionsService {
	
	def invoicesService

	def map(transaction) {
		[
			id : transaction.id,
			merchant : transaction.merchant.id
		]
	}

	def list() {
		Transaction.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def transaction = Transaction.newInstance(
			merchant : Merchant.load(merchantId)
		).save(failOnError : true, flush : true)
		entity.invoices.each {
			Invoice.load(it).transaction = Transaction.load(transaction.id)
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
