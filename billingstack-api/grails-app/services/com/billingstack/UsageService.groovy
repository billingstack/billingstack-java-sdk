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

	def create(merchantId, entity) {
		def list = []
		entity.each {
			list << Usage.newInstance(
				subscription : Subscription.load(it.subscription_id),
				product : Product.load(it.product_id),
				volume : it.volume,
				start : it.start,
				end : it.end
			).save(flush : true, failOnError : true)
		}
		entity.collect { map(it) }
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
