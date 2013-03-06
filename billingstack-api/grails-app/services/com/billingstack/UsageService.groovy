package com.billingstack

class UsageService {

	def map(usage) {
		[
			id : usage.id,
			subscription_id : usage.subscription.id,
			product_id : usage.product.id,
			volume : usage.volume,
			start : usage.start,
			end : usage.end
		]
	}

	def list() {
		Usage.list().collect { map(it) }
	}

	def create(subscriptionId, entity) {
		def usage = Usage.newInstance(
			subscription : Subscription.load(subscriptionId),
			product : Product.load(entity.product_id),
			volume : entity.volume,
			start : entity.start,
			end : entity.end
		)
		map(usage.save(flush : true, failOnError : true))
	}

	def show(String usageId) {
		map(Usage.get(usageId))
	}

	def update(entity) {
		def usage = Usage.get(entity.id)
		map(usage)
	}

	def delete(String usageId) {
		Usage.load(usageId).delete(flush : true)
	}

}
