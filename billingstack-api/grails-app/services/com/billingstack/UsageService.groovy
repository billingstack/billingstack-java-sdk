package com.billingstack

class UsageService {

	def map(usage) {
		[
			id : usage.id,
			subscription_id : usage.subscription.id,
			subscription_name : usage.subscription.plan.name,
			product_id : usage.product.id,
			product_name : usage.product.name,
			volume : usage.volume,
			start : usage.start,
			end : usage.end
		]
	}
	
	def list(String merchantId, filters) {
		def ops = filters.list('q.op')
		def names = filters.list('q.field')
		def values = filters.list('q.value')
		Usage.createCriteria().list {
			subscription {
				customer {
					eq 'merchant.id', merchantId
				}
			}
			for(int i = 0; i < ops.size(); i++) {
				if("customer_id".equals(names.get(i))) {
					subscription {
						customer {
							"${ops.get(i)}"('id',values.get(i))
						}
					}
				}
				if("subscription_id".equals(names.get(i))) {
					eq 'subscription.id', values.get(i)
				}
			}
		}.collect { map(it) }
	}

	def list(filters) {
		def f = [:]
		if(filters.customer_id) {
			f['customer.id'] = filters.customer_id
		}
		Usage.findAllWhere(f).collect { map(it) }
	}

	def create(merchantId, entity) {
		def now = new Date()
		def past = now - 30
		def u = Usage.newInstance(
				subscription : Subscription.load(entity.subscription_id),
				product : Product.findByName(entity.product_name),
				volume : entity.volume,
				start : past.time,
				end : now.time
				//start : it.start,
				//end : it.end
		).save(flush : true, failOnError : true)
		map(u)
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
